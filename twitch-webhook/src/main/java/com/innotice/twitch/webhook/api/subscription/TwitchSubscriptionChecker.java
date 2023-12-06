package com.innotice.twitch.webhook.api.subscription;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TwitchSubscriptionChecker {

    private final WebClient twitchWebClient;

    public TwitchSubscriptionChecker(@Qualifier("twitchWebClient") WebClient twitchWebClient) {
        this.twitchWebClient = twitchWebClient;
    }

    public Mono<Boolean> isStreamOnlineSubscriptionExist(Long streamerId) {
        return getSubscriptionsByType("stream.online")
                .collectList()
                .map(subscriptions -> subscriptions.stream()
                        .map(responseData -> responseData.getCondition().get("broadcaster_user_id"))
                        .map(Long::valueOf)
                        .anyMatch(userId -> userId.equals(streamerId))
                );
    }

    private Flux<TwitchSubscriptionResponse.ResponseData> getSubscriptionsByType(String type) {
        return twitchWebClient.get()
                .uri("https://api.twitch.tv/helix/eventsub/subscriptions")
                .attribute("type", type)
                .attribute("status", "enabled")
                .retrieve()
                .bodyToMono(TwitchSubscriptionResponse.class)
                .flatMapMany(response -> Flux.fromStream(response.getData().stream()));
    }

}
