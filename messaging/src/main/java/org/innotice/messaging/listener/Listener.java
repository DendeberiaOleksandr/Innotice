package org.innotice.messaging.listener;

public interface Listener<T> {

    void listen(T t);

}
