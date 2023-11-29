package com.innotice.messaging.publisher.exception;

public class PublisherException extends RuntimeException {

    public PublisherException() {
    }

    public PublisherException(String message) {
        super(message);
    }

    public PublisherException(String message, Throwable cause) {
        super(message, cause);
    }

    public PublisherException(Throwable cause) {
        super(cause);
    }

    public PublisherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
