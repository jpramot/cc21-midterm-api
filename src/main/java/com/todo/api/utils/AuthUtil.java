package com.todo.api.utils;

import com.todo.api.entity.User;
import com.todo.api.exceptionHandler.NotFoundExc;
import com.todo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    @Autowired
    private UserRepository userRepository;

    public User loginUser() {
        Authentication authentication = getAuthentication();
        String username = authentication.getName();
        // check if user is anonymous
        if(username.equals("anonymousUser")) {
            throw new NotFoundExc("User", "username", username);
        } else {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundExc("User", "username", username));
        }
    }

    public Long getUserId() {
        User user = loginUser();
        return user.getId();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
