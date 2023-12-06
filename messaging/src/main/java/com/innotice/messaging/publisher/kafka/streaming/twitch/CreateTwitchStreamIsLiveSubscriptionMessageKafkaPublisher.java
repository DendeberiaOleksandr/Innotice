package com.innotice.messaging.publisher.kafka.streaming.twitch;

import com.innotice.model.messaging.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher extends TwitchMessageKafkaPublisher<CreateTwitchStreamIsLiveSubscriptionMessage> {
    protected CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher(NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        super(newTopic, kafkaTemplate);
    }
}
