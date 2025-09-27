package com.todo.api.config;

import com.todo.api.entity.User;
import com.todo.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return runner ->{
            User existsUser = userRepository.findById(1L).orElse(null);
//            log.info("----------> run mysql url: {}", MYSQL_URL);
            if(existsUser == null) {
                for(int i = 1; i <= 50; i++){
                    String username = "todo_user" + i;
                    String password = "password" + i;
                    String hashed = passwordEncoder.encode(password);
                    User user = new User().setUsername(username).setPassword(hashed);
                    userRepository.save(user);
                }
                String username = "todoadmin";
                String password = "123456";
                String hashed = passwordEncoder.encode(password);
                User user = new User().setUsername(username).setPassword(hashed);
                userRepository.save(user);
                log.info("create user success");
            }else {
                log.info("user already exists");
            }
        };
    }
}
