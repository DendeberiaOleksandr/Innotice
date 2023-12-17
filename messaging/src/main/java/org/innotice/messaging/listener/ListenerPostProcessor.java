package org.innotice.messaging.listener;

import com.innotice.model.messaging.Message;
import reactor.core.publisher.Mono;

public interface ListenerPostProcessor<T extends Message> {
    Mono<Void> process(Mono<T> t);
}
