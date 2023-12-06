package com.innotice.security.microservice.web;

import com.innotice.security.microservice.SecretHeaderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class SecretWebClientConfig {

    private final SecretHeaderProperties secretHeaderProperties;

    @Bean("secretWebClient")
    public WebClient secretWebClient() {
        return WebClient.builder()
                .defaultHeader(secretHeaderProperties.getHeaderName(), secretHeaderProperties.getSecret())
                .build();
    }

}
