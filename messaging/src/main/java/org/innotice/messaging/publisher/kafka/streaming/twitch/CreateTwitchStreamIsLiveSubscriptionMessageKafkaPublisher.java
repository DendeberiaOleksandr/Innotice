package org.innotice.messaging.publisher.kafka.streaming.twitch;

import org.innotice.messaging.message.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher extends TwitchMessageKafkaPublisher<CreateTwitchStreamIsLiveSubscriptionMessage> {
    protected CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher(@Qualifier("createTwitchStreamIsLiveSubscription") NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        super(newTopic, kafkaTemplate);
    }
}
