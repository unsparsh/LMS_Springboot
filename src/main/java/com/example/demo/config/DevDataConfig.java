package com.example.demo.config;

import com.example.demo.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DevDataConfig {
    @Bean
    @Profile("dev")
    CommandLineRunner seedUsers(AuthService authService) {
        return args -> authService.createDevUsersIfMissing();
    }
}
