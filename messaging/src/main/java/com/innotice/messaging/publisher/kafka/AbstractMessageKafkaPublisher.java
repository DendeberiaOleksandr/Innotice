package com.innotice.messaging.publisher.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innotice.messaging.publisher.Publisher;
import com.innotice.messaging.publisher.exception.PublisherException;
import com.innotice.model.messaging.AbstractMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractMessageKafkaPublisher<T extends AbstractMessage> implements Publisher<T> {
    private final NewTopic newTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected AbstractMessageKafkaPublisher(NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes message
     * @param abstractMessage
     * @return
     * @throws PublisherException
     */
    @Override
    public Mono<SendResult<String, String>> publish(T abstractMessage) {
        try {
            return Mono.fromFuture(kafkaTemplate.send(newTopic.name(), OBJECT_MAPPER.writeValueAsString(abstractMessage)));
        } catch (JsonProcessingException e) {
            log.error("publish: {}", e.getMessage());
            throw new PublisherException(e);
        }
    }
}
