package com.springbootsse.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] matchedPaths = { 
            "/", 
            "/api/**"
        };

        http
        .cors(org.springframework.security.config.Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .securityMatcher(
            matchedPaths
        )
        .authorizeHttpRequests(
            request -> 
            request
                .requestMatchers(matchedPaths)
                .permitAll()
        );


        return http.build();
    }
}
