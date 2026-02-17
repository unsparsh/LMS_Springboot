package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class CamundaClientConfig {

    @Bean
    RestClient camundaRestClient(@Value("${camunda.base-url}") String baseUrl,
                                 @Value("${camunda.auth.token:}") String token) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, token.isBlank() ? "" : "Bearer " + token)
                .build();
    }
}
