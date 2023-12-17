package org.innotice.twitch.service.api.subscription;

import org.innotice.messaging.publisher.kafka.streaming.twitch.CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import com.innotice.model.messaging.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.innotice.twitch.service.web.DiscordServerResourceServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OnStartupTwitchEventSubscriber {

    private final CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher;
    private final DiscordServerResourceServerService discordServerResourceServerService;

    @Autowired
    public OnStartupTwitchEventSubscriber(CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher,
                                          DiscordServerResourceServerService discordServerResourceServerService) {
        this.publisher = publisher;
        this.discordServerResourceServerService = discordServerResourceServerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public Mono<Void> subscribeOnStreamIsLiveEvents() {
        return getStreamersIdToSubscribeOnIsLiveEvent()
                .flatMap(streamerId -> publisher.publish(new CreateTwitchStreamIsLiveSubscriptionMessage(streamerId)))
                .then();
    }

    private Flux<Long> getStreamersIdToSubscribeOnIsLiveEvent() {
        return discordServerResourceServerService
                .findAll()
                .flatMap(discordServer -> Flux.fromStream(discordServer.getStreamerIsLiveSubscriptions().stream()))
                .map(StreamerIsLiveSubscription::getStreamerId);
    }
}
