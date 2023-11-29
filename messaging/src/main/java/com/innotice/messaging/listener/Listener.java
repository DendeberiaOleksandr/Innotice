package com.innotice.messaging.listener;

import reactor.core.publisher.Mono;

public interface Listener<T> {

    Mono<Void> listen(T t);

}
