package org.innotice.twitch.service.config;

import lombok.RequiredArgsConstructor;
import org.innotice.security.microservice.web.WebClientResponseLoggingFilterFunction;
import org.innotice.twitch.service.security.TwitchSecurityCredentials;
import org.innotice.twitch.service.security.filter.TwitchAuthorizationTokenExchangeFilterFunction;
import lombok.extern.slf4j.Slf4j;
import org.innotice.security.microservice.web.WebClientRequestLoggingFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TwitchAuthWebClientConfig {

    private final TwitchAuthorizationTokenExchangeFilterFunction tokenExchangeFilterFunction;
    private final WebClientRequestLoggingFilterFunction webClientRequestLoggingFilterFunction;
    private final TwitchSecurityCredentials twitchSecurityCredentials;
    private final WebClientResponseLoggingFilterFunction webClientResponseLoggingFilterFunction;

    @Bean("twitchWebClient")
    public WebClient twitchWebClient() {
        return WebClient.builder()
                .filter(tokenExchangeFilterFunction)
                .filter(webClientRequestLoggingFilterFunction)
                .defaultHeader("Client-Id", twitchSecurityCredentials.getClientId())
                .build();
    }

}
