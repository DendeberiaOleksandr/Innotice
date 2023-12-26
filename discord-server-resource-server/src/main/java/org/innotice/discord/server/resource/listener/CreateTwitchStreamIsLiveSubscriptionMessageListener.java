package org.innotice.discord.server.resource.listener;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import lombok.extern.slf4j.Slf4j;
import org.innotice.discord.server.resource.service.DiscordServerReactiveService;
import org.innotice.messaging.listener.kafka.AbstractKafkaListener;
import org.innotice.messaging.message.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Set;

import static org.innotice.discord.server.resource.consts.KafkaConstants.KAFKA_GROUP_ID;
import static org.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC;

@Slf4j
@Component
public class CreateTwitchStreamIsLiveSubscriptionMessageListener extends AbstractKafkaListener<CreateTwitchStreamIsLiveSubscriptionMessage> {

    private final DiscordServerReactiveService service;

    public CreateTwitchStreamIsLiveSubscriptionMessageListener(DiscordServerReactiveService service) {
        super(CreateTwitchStreamIsLiveSubscriptionMessage.class);
        this.service = service;
    }

    @KafkaListener(topics = CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC, groupId = KAFKA_GROUP_ID)
    public void listen(String message) {
        log.info("listen: {}", message);
        convertMessage(message)
                .flatMap(createTwitchStreamIsLiveSubscription -> service.upsert(new DiscordServer(
                        createTwitchStreamIsLiveSubscription.getDiscordServerId(),
                        Set.of(new StreamerIsLiveSubscription(
                                createTwitchStreamIsLiveSubscription.getStreamerId(),
                                createTwitchStreamIsLiveSubscription.getStreamerName(),
                                createTwitchStreamIsLiveSubscription.getDiscordChannelId(),
                                false
                        )),
                        LocalDateTime.now()
                )))
                .subscribe();
    }
}
