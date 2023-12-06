package com.innotice.messaging.listener.kafka.streaming.twitch;

import com.innotice.messaging.listener.ListenerPostProcessor;
import com.innotice.messaging.listener.kafka.AbstractKafkaListener;
import com.innotice.model.messaging.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC;

@Component
public class CreateTwitchStreamIsLiveSubscriptionKafkaListener extends AbstractKafkaListener<CreateTwitchStreamIsLiveSubscriptionMessage> {
    public CreateTwitchStreamIsLiveSubscriptionKafkaListener(List<? extends ListenerPostProcessor<CreateTwitchStreamIsLiveSubscriptionMessage>> listenerPostProcessors) {
        super(CreateTwitchStreamIsLiveSubscriptionMessage.class, listenerPostProcessors);
    }

    @KafkaListener(topics = CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC)
    @Override
    public Mono<Void> listen(String s) {
        return super.listenImpl(s);
    }
}
