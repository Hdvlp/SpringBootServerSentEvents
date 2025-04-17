package com.springbootsse.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] domains = new String[]{
        "http://localhost:8080",
        "http://127.0.0.1:8080",
        "http://[::1]:8080"
    };
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/sse/subscribe")
                .allowedOrigins(domains) 
                .allowedMethods("GET");
    }
}
