package org.innotice.twitch.service.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.innotice.twitch.service.security.TwitchAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Function;

@Slf4j
@Component
public class TwitchAuthorizationTokenExchangeFilterFunction implements ExchangeFilterFunction {

    private final TwitchAuthorizationService twitchAuthorizationService;

    @Autowired
    public TwitchAuthorizationTokenExchangeFilterFunction(TwitchAuthorizationService twitchAuthorizationService) {
        this.twitchAuthorizationService = twitchAuthorizationService;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return twitchAuthorizationService.getAccessToken()
                        .map(accessToken -> ClientRequest.from(request)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(accessToken))
                                    .build())
                        .flatMap(next::exchange);

//                                .retryWhen(
//                                        Retry.backoff(2, Duration.ofSeconds(2L))
//                                                .filter(this::isAccessDeniedResponseException)
//                                                .doBeforeRetry(retrySignal -> log.error("Twitch has responded with code 401 so will retry to send a request"))
//                                                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure())));
    }

    private boolean isAccessDeniedResponseException(Throwable throwable) {
        return throwable instanceof IllegalAccessError;
    }


}
