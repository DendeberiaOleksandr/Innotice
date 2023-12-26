package org.innotice.messaging.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.innotice.messaging.listener.Listener;
import org.innotice.messaging.message.Message;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractKafkaListener<T extends Message> implements Listener<String> {

    private final ObjectMapper OBJECT_MAPPER;
    private final Class<? extends T> messageClass;

    protected AbstractKafkaListener(Class<? extends T> messageClass) {
        this.messageClass = messageClass;
        OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    protected Mono<? extends T> convertMessage(String message) {
        try {
            return Mono.just(OBJECT_MAPPER.readValue(message, messageClass));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
