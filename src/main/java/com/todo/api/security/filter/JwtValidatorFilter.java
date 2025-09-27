package com.todo.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import com.todo.api.security.jwt.JwtUtil;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JwtValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.getJwtFromHeader(request);
        String path = request.getServletPath();

        if(token != null) {
            try{
                Claims claims = Jwts.parser()
                        .verifyWith(jwtUtil.key())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                String username = (String) claims.get("username");
                List<GrantedAuthority> authority = AuthorityUtils
                        .commaSeparatedStringToAuthorityList((String) claims.get("authority"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authority);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (MalformedJwtException malExc) {
               String message = "Token malformed";
               sendError(response, message, path);
               return;
            }catch (ExpiredJwtException expExc) {
                String message = "Token expired";
                sendError(response, message, path);
                return;
            }catch (UnsupportedJwtException unSupportExc) {
                String message = "Token unsupported";
                sendError(response, message, path);
                return;
            }catch (JwtException exc) {
                sendError(response,exc.getMessage(), path);
                return;
            }
        }
            filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, String message, String path) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorApiRes res = new ErrorApiRes(message,"Authorization error", path);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(response.getOutputStream(), res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        Set<String> pathToExclude = Set.of("/api/V1/auth/login", "/api/V1/auth/register", "api/V1/auth/test");
        return pathToExclude.contains(path);
    }
}
