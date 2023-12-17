package org.innotice.messaging.publisher.kafka.streaming.twitch;

import org.innotice.messaging.publisher.kafka.AbstractMessageKafkaPublisher;
import com.innotice.model.messaging.stream.twitch.TwitchMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;

public abstract class TwitchMessageKafkaPublisher<T extends TwitchMessage> extends AbstractMessageKafkaPublisher<T> {
    protected TwitchMessageKafkaPublisher(NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        super(newTopic, kafkaTemplate);
    }
}
