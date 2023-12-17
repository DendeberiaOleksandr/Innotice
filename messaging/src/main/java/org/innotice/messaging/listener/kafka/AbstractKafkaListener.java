package org.innotice.messaging.listener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.innotice.messaging.listener.Listener;
import org.innotice.messaging.listener.ListenerPostProcessor;
import org.innotice.messaging.listener.exception.ListenerException;
import com.innotice.model.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public abstract class AbstractKafkaListener<T extends Message> implements Listener<String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Class<? extends T> messageClass;
    private final List<? extends ListenerPostProcessor<T>> postProcessors;

    protected AbstractKafkaListener(Class<? extends T> messageClass,
                                    List<? extends ListenerPostProcessor<T>> postProcessors) {
        this.messageClass = messageClass;
        this.postProcessors = postProcessors;
    }

    protected Mono<Void> listenImpl(String message) {
        log.info("listen: {}", message);
        return listenConverted(Mono.just(message).map(s -> {
            try {
                return OBJECT_MAPPER.readValue(s, messageClass);
            } catch (JsonProcessingException e) {
                log.error("listen: {}", e.getMessage());
                throw new ListenerException(e);
            }
        }));
    }

    private Mono<Void> listenConverted(Mono<T> t) {
        return Flux.fromStream(postProcessors.stream())
                .flatMap(postProcessor -> postProcessor.process(t))
                .then();
    }
}
