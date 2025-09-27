package com.todo.api.service;

import com.todo.api.dtos.requestDto.LoginReq;
import com.todo.api.dtos.requestDto.RegisterReq;
import com.todo.api.dtos.responseDto.LoginRes;
import com.todo.api.dtos.responseDto.RegisterRes;
import com.todo.api.entity.User;
import com.todo.api.exceptionHandler.BadRequestExc;
import com.todo.api.repository.UserRepository;
import com.todo.api.security.jwt.JwtUtil;
import com.todo.api.security.user.UserDetailImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public RegisterRes register(RegisterReq body) {
        if(!body.getPassword().equals(body.getConfirmPassword())) {
            throw new BadRequestExc("Password and confirm password does not match");
        }
        User existsUser = userRepository.findByUsername(body.getUsername()).orElse(null);
        if(existsUser!= null) throw new BadRequestExc("User already exists");

        String hashed = passwordEncoder.encode(body.getPassword());
        User user = new User().setUsername(body.getUsername()).setPassword(hashed);
        userRepository.save(user);
        return new RegisterRes("User registered successfully", true);
    }

    @Override
    public LoginRes login(LoginReq body) {

        Authentication authUser = authenticateUser(body);
        SecurityContextHolder.getContext().setAuthentication(authUser);

        UserDetailImpl user = (UserDetailImpl) authUser.getPrincipal();

        String accessToken = jwtUtil.generateJwtToken(user);

        return new LoginRes(accessToken, user.getId());
    }

    private Authentication authenticateUser(LoginReq body) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        return authenticationManager.authenticate(authentication);
    }
}
