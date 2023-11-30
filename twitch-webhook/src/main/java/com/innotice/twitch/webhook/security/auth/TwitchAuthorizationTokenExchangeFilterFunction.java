package com.innotice.twitch.webhook.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Component
public class TwitchAuthorizationTokenExchangeFilterFunction implements ExchangeFilterFunction {

    private final TwitchAuthorizationService twitchAuthorizationService;

    @Autowired
    public TwitchAuthorizationTokenExchangeFilterFunction(TwitchAuthorizationService twitchAuthorizationService) {
        this.twitchAuthorizationService = twitchAuthorizationService;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        String accessToken = twitchAuthorizationService.getAccessToken().block();
        request.headers().add(HttpHeaders.AUTHORIZATION, accessToken);
        return next.exchange(request);
    }
}
