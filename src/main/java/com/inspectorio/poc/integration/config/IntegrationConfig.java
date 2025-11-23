package com.inspectorio.poc.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class IntegrationConfig {

    @Value("${inspectorio.api.base-url}")
    private String baseUrl;

    @Value("${inspectorio.api.key}")
    private String apiKey;

    @Bean
    public RestClient inspectorioRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + (apiKey != null ? apiKey : ""))
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
