package org.innotice.twitch.service.config;

import org.innotice.twitch.service.security.TwitchAuthorizationTokenExchangeFilterFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class TwitchAuthWebClientConfig {

    private final TwitchAuthorizationTokenExchangeFilterFunction tokenExchangeFilterFunction;

    public TwitchAuthWebClientConfig(TwitchAuthorizationTokenExchangeFilterFunction tokenExchangeFilterFunction) {
        this.tokenExchangeFilterFunction = tokenExchangeFilterFunction;
    }

    @Bean("twitchWebClient")
    public WebClient twitchWebClient() {
        return WebClient.builder()
                .filter(tokenExchangeFilterFunction)
                .build();
    }

}
