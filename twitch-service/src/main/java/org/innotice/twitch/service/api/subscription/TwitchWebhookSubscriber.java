package org.innotice.twitch.service.api.subscription;

import org.innotice.messaging.listener.ListenerPostProcessor;
import com.innotice.model.messaging.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.innotice.twitch.service.api.subscription.exception.TwitchSubscriptionAlreadyExistsException;import org.innotice.twitch.service.security.TwitchSecurityCredentials;import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Component
@Slf4j
public class TwitchWebhookSubscriber implements ListenerPostProcessor<CreateTwitchStreamIsLiveSubscriptionMessage> {

    private final WebClient twitchWebClient;
    private final TwitchSecurityCredentials twitchSecurityCredentials;
    private final TwitchSubscriptionChecker subscriptionChecker;

    public TwitchWebhookSubscriber(@Qualifier("twitchWebClient") WebClient twitchWebClient,
                                   TwitchSecurityCredentials twitchSecurityCredentials,
                                   TwitchSubscriptionChecker subscriptionChecker) {
        this.twitchWebClient = twitchWebClient;
        this.twitchSecurityCredentials = twitchSecurityCredentials;
        this.subscriptionChecker = subscriptionChecker;
    }

    @Override
    public Mono<Void> process(Mono<CreateTwitchStreamIsLiveSubscriptionMessage> event) {
        return event.flatMap(e -> subscribeOnStreamIsLive(e.getStreamerId()));
    }

    private Mono<Void> subscribeOnStreamIsLive(Long streamerId) {
        return subscriptionChecker.isStreamOnlineSubscriptionExist(streamerId)
                .flatMap(exist -> {
                    if (!exist) {
                        log.info("Subscribing for the stream is online event for the stream: {}", streamerId);
                        return twitchWebClient.post()
                                .uri("https://api.twitch.tv/helix/eventsub/subscriptions")
                                .header("Client-Id", twitchSecurityCredentials.getClientId())
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .bodyValue(subscribeOnStreamIsLiveRequestBody(streamerId))
                                .retrieve()
                                .onRawStatus(status -> status == 409, clientResponse -> Mono.error(twitchSubscriptionAlreadyExistsException(streamerId)))
                                .bodyToMono(TwitchSubscriptionResponse.class);
                    }
                    log.info("Already subscribed for the stream is online event for the stream: {}", streamerId);
                    return Mono.empty();
                })
                .onErrorContinue((throwable, o) -> {
                    log.info(throwable.getMessage());
                })
                .log()
                .then();
    }

    private TwitchSubscriptionAlreadyExistsException twitchSubscriptionAlreadyExistsException(Long streamerId) {
        return new TwitchSubscriptionAlreadyExistsException(String.format("Already subscribed for streamer: %d", streamerId));
    }

    private TwitchSubscriptionRequest subscribeOnStreamIsLiveRequestBody(Long streamerId) {
        try {
            return TwitchSubscriptionRequest.builder()
                    .type(TwitchSubscriptionTypes.STREAM_ONLINE.getName())
                    .version("1")
                    .condition(Map.of("broadcaster_user_id", String.valueOf(streamerId)))
                    .transport(Map.of(
                            "method", TwitchSubscriptionTransportMethods.WEBHOOK.getName(),
                            "callback", String.format("https://%s/webhooks/callback", InetAddress.getLocalHost().getHostAddress()),
                            "secret", twitchSecurityCredentials.getClientSecret()
                    ))
                    .build();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
