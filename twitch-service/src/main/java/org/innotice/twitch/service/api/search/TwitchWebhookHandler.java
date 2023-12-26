package org.innotice.twitch.service.api.search;

import com.innotice.model.domain.discord.server.DiscordServerFilter;
import com.innotice.model.domain.twitch.Condition;
import com.innotice.model.domain.twitch.Event;
import com.innotice.model.domain.twitch.Subscription;
import com.innotice.model.domain.twitch.SubscriptionWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.innotice.messaging.message.stream.twitch.TwitchStreamerIsLiveMessage;
import org.innotice.messaging.publisher.kafka.streaming.twitch.TwitchStreamerIsLiveMessageKafkaPublisher;
import org.innotice.web.services.DiscordServerResourceServerService;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/webhooks/callback")
@RequiredArgsConstructor
public class TwitchWebhookHandler {

    private final TwitchStreamerIsLiveMessageKafkaPublisher publisher;
    private final DiscordServerResourceServerService discordServerResourceServerService;

    @PostMapping
    public Mono<ResponseEntity<?>> callback(@RequestBody SubscriptionWrapper subscriptionWrapper) {
        log.info("callback: {}", subscriptionWrapper);

        Event event = subscriptionWrapper.getEvent();
        Long streamerId = Long.valueOf(event.getBroadcasterUserId());
        return discordServerResourceServerService.findAll(new DiscordServerFilter(null, null, null, Set.of(40488774L)))
                .flatMap(discordServer -> Flux.fromStream(discordServer.getStreamerIsLiveSubscriptions().stream()
                        .map(streamerIsLiveSubscription -> Pair.of(discordServer.getId(), streamerIsLiveSubscription.getDiscordChatId())))
                )
                .flatMap(serverIdChatId -> {
                    TwitchStreamerIsLiveMessage message = new TwitchStreamerIsLiveMessage();
                    message.setIssuedAt(LocalDateTime.now());
                    message.setStreamerId(streamerId);
                    message.setStreamerName(event.getBroadcasterUserName());
                    message.setDiscordServerId(serverIdChatId.getFirst());
                    message.setDiscordChannelId(serverIdChatId.getSecond());
                    return publisher.publish(message);
                })
                .log()
                .then(Mono.just(ResponseEntity.status(HttpStatus.OK).build()));
    }

}
