package org.innotice.messaging.publisher.kafka.streaming.twitch;

import org.innotice.messaging.message.stream.twitch.TwitchStreamerIsLiveMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TwitchStreamerIsLiveMessageKafkaPublisher extends TwitchMessageKafkaPublisher<TwitchStreamerIsLiveMessage>{

    @Autowired
    protected TwitchStreamerIsLiveMessageKafkaPublisher(@Qualifier("twitchStreamerIsLiveTopic") NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        super(newTopic, kafkaTemplate);
    }
}
