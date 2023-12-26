package org.innotice.messaging.publisher.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.innotice.messaging.publisher.Publisher;
import org.innotice.messaging.publisher.exception.PublisherException;
import org.innotice.messaging.message.AbstractMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractMessageKafkaPublisher<T extends AbstractMessage> implements Publisher<T> {
    private final NewTopic newTopic;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper OBJECT_MAPPER;

    protected AbstractMessageKafkaPublisher(NewTopic newTopic, KafkaTemplate<String, String> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
        OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
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
