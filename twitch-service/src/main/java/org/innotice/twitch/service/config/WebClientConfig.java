package org.innotice.twitch.service.config;

import org.innotice.security.web.WebClientRequestLoggingFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final WebClientRequestLoggingFilterFunction webClientRequestLoggingFilterFunction;

    public WebClientConfig(WebClientRequestLoggingFilterFunction webClientRequestLoggingFilterFunction) {
        this.webClientRequestLoggingFilterFunction = webClientRequestLoggingFilterFunction;
    }

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .filter(webClientRequestLoggingFilterFunction)
                .build();
    }

}
