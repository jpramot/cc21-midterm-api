package com.todo.api.security.jwt;

import com.todo.api.security.user.UserDetailImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${JWT.SECRET}")
    private String JWT_SECRET;

    @Value("${JWT.EXPIRATION}")
    private int JWT_EXPIRATION;

    public String getJwtFromHeader(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(UserDetailImpl userDetail) {

        String username = userDetail.getUsername();
        String authority = userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return Jwts.builder()
                .claim("username", username)
                .claim("authority", authority)
                .issuer("Todo API")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(JWT_EXPIRATION)))
                .signWith(key())
                .compact();
    }
}
