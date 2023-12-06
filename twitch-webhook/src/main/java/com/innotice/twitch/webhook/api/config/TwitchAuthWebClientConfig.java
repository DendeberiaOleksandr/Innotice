package com.innotice.twitch.webhook.api.config;

import com.innotice.twitch.webhook.security.auth.TwitchAuthorizationTokenExchangeFilterFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
