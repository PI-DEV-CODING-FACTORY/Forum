package com.esprit.pidev.codingfactory.chatgptBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    private String openaiApiKey = "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";

    @Bean
    public RestTemplate template() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}