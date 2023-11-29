package com.innotice.messaging.publisher;

import reactor.core.publisher.Mono;

public interface Publisher<T> {

    Mono<?> publish(T t);

}
