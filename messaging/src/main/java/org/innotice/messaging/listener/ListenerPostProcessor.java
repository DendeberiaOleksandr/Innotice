package org.innotice.messaging.listener;

import org.innotice.messaging.message.Message;
import reactor.core.publisher.Mono;

public interface ListenerPostProcessor<T extends Message> {
    Mono<Void> process(Mono<T> t);
}
