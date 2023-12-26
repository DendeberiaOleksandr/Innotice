package org.innotice.twitch.service.listener;

import com.innotice.model.domain.discord.server.DiscordServerFilter;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import org.innotice.messaging.listener.ListenerPostProcessor;
import org.innotice.messaging.listener.kafka.AbstractKafkaListener;
import org.innotice.messaging.message.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.innotice.twitch.service.api.subscription.TwitchSubscriptionChecker;
import org.innotice.twitch.service.api.subscription.TwitchSubscriptionRequest;
import org.innotice.twitch.service.api.subscription.TwitchSubscriptionResponse;
import org.innotice.twitch.service.api.subscription.TwitchSubscriptionTransportMethods;
import org.innotice.twitch.service.api.subscription.TwitchSubscriptionTypes;
import org.innotice.twitch.service.api.subscription.exception.TwitchSubscriptionAlreadyExistsException;import org.innotice.twitch.service.security.TwitchSecurityCredentials;import lombok.extern.slf4j.Slf4j;
import org.innotice.web.services.DiscordServerResourceServerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import static org.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC;
import static org.innotice.twitch.service.consts.KafkaConstants.GROUP_ID;

@Component
@Slf4j
public class TwitchCreateTwitchStreamIsLiveSubscriber extends AbstractKafkaListener<CreateTwitchStreamIsLiveSubscriptionMessage> {

    private final WebClient twitchWebClient;
    private final TwitchSecurityCredentials twitchSecurityCredentials;
    private final TwitchSubscriptionChecker subscriptionChecker;
    private final DiscordServerResourceServerService discordServerResourceServerService;

    public TwitchCreateTwitchStreamIsLiveSubscriber(@Qualifier("twitchWebClient") WebClient twitchWebClient,
                                                    TwitchSecurityCredentials twitchSecurityCredentials,
                                                    TwitchSubscriptionChecker subscriptionChecker,
                                                    DiscordServerResourceServerService discordServerResourceServerService) {
        super(CreateTwitchStreamIsLiveSubscriptionMessage.class);
        this.twitchWebClient = twitchWebClient;
        this.twitchSecurityCredentials = twitchSecurityCredentials;
        this.subscriptionChecker = subscriptionChecker;
        this.discordServerResourceServerService = discordServerResourceServerService;
    }

    @KafkaListener(topics = CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC, groupId = GROUP_ID)
    @Override
    public void listen(String s) {
        super.convertMessage(s)
                .flatMap(this::subscribeOnStreamIsLive)
                .subscribe();
    }

    private Mono<Void> subscribeOnStreamIsLive(CreateTwitchStreamIsLiveSubscriptionMessage message) {
        Long streamerId = message.getStreamerId();
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
                                .bodyToMono(TwitchSubscriptionResponse.class)
                                .flatMapMany(twitchSubscriptionResponse -> discordServerResourceServerService.findAll(
                                        DiscordServerFilter.builder()
                                                .id(message.getDiscordServerId())
                                                .build()
                                ))
                                .flatMap(discordServer -> {
                                    discordServer.getStreamerIsLiveSubscriptions().add(new StreamerIsLiveSubscription(
                                            message.getStreamerId(), message.getStreamerName(), message.getDiscordChannelId(), true
                                    ));
                                    return discordServerResourceServerService.save(discordServer);
                                })
                                .log()
                                .then();
                    }
                    log.info("Already subscribed for the stream is online event for the stream: {}", streamerId);
                    return Mono.empty();
                })
                .onErrorContinue((throwable, o) -> {
                    log.error(throwable.getMessage());
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
