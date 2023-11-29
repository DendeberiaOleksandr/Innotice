package com.innotice.messaging.publisher.kafka.streaming.twitch;

import com.innotice.model.messaging.stream.twitch.TwitchStreamerIsLiveMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.TWITCH_STREAMER_IS_LIVE_TOPIC;

@Component
public class TwitchStreamerIsLiveMessageKafkaPublisher extends TwitchMessageKafkaPublisher<TwitchStreamerIsLiveMessage>{

    @Autowired
    protected TwitchStreamerIsLiveMessageKafkaPublisher(@Qualifier(TWITCH_STREAMER_IS_LIVE_TOPIC) NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        super(newTopic, kafkaTemplate);
    }
}
