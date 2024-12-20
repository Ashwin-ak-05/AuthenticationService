//package com.example.AuthUserService.configurations;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurations {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> {
//            try {
//                requests.anyRequest().permitAll()
//                        .and().cors().disable()
//                        .csrf().disable();
//            } catch (Exception e) {
//                System.out.println("error occurred in verifying request " + e.getMessage());
//            }
//        });
//
//        return http.build();
//    }
//
//}