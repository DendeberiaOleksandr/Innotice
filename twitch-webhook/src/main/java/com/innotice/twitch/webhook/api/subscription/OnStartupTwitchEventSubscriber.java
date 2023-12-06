package com.innotice.twitch.webhook.api.subscription;

import com.innotice.messaging.publisher.kafka.streaming.twitch.CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher;
import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import com.innotice.model.messaging.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OnStartupTwitchEventSubscriber {

    private final CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher;
    private final WebClient secretWebClient;

    public OnStartupTwitchEventSubscriber(CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher,
                                          @Qualifier("secretWebClient") WebClient secretWebClient) {
        this.publisher = publisher;
        this.secretWebClient = secretWebClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public Mono<Void> subscribeOnStreamIsLiveEvents() {
        return getStreamersIdToSubscribeOnIsLiveEvent()
                .flatMap(streamerId -> publisher.publish(new CreateTwitchStreamIsLiveSubscriptionMessage(streamerId)))
                .then();
    }

    private Flux<Long> getStreamersIdToSubscribeOnIsLiveEvent() {
        return secretWebClient.get()
                .uri("DISCORD-SERVERS-RESOURCE-SERVER")
                .retrieve()
                .bodyToFlux(DiscordServer.class)
                .flatMap(discordServer -> Flux.fromStream(discordServer.getStreamerIsLiveSubscriptions().stream()))
                .map(StreamerIsLiveSubscription::getStreamerId);
    }

}
