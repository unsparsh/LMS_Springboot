package com.example.demo.config;

import com.example.demo.service.CamundaProcessService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaDeploymentRunner {
    @Bean
    CommandLineRunner deployOnStartup(CamundaProcessService processService) {
        return args -> processService.deployResources();
    }
}
