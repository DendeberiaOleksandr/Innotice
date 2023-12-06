package com.innotice.messaging.listener.kafka.streaming.twitch;

import com.innotice.messaging.listener.ListenerPostProcessor;
import com.innotice.messaging.listener.kafka.AbstractKafkaListener;
import com.innotice.model.messaging.stream.twitch.TwitchStreamerIsLiveMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.TWITCH_STREAMER_IS_LIVE_TOPIC;

@Component
public class TwitchStreamerIsLiveKafkaListener extends AbstractKafkaListener<TwitchStreamerIsLiveMessage> {

    public TwitchStreamerIsLiveKafkaListener(List<? extends ListenerPostProcessor<TwitchStreamerIsLiveMessage>> listenerPostProcessors) {
        super(TwitchStreamerIsLiveMessage.class, listenerPostProcessors);
    }

    @KafkaListener(topics = TWITCH_STREAMER_IS_LIVE_TOPIC)
    @Override
    public Mono<Void> listen(String string) {
        return super.listenImpl(string);
    }
}
