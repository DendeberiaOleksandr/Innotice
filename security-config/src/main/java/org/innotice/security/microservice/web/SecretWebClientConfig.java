package org.innotice.security.microservice.web;

import lombok.RequiredArgsConstructor;
import org.innotice.security.microservice.SecretHeaderProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class SecretWebClientConfig {

    private final SecretHeaderProperties secretHeaderProperties;

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder()
                .defaultHeader(secretHeaderProperties.getHeaderName(), secretHeaderProperties.getSecret());
    }

}
