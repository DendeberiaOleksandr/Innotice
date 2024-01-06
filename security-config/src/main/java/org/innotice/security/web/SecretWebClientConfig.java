package org.innotice.security.web;

import lombok.RequiredArgsConstructor;
import org.innotice.security.InternalSecretProperties;
import org.innotice.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class SecretWebClientConfig {

    private final InternalSecretProperties internalSecretProperties;
    private final WebClientRequestLoggingFilterFunction webClientRequestLoggingFilterFunction;

    @Value("${spring.application.name}")
    private String applicationName;

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder()
                .filter(webClientRequestLoggingFilterFunction)
                .defaultHeader(SecurityUtils.INTERNAL_SERVICE_HEADER, applicationName)
                .defaultHeader(SecurityUtils.AUTH_PROVIDER_HEADER, internalSecretProperties.getSecret());
    }

}
