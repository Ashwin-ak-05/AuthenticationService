package com.example.AuthUserService.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Bean
    public BCryptPasswordEncoder createBcrptBean(){
        return new BCryptPasswordEncoder();
    }
}
