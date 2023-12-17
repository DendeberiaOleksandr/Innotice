package org.innotice.twitch.service.api.subscription.exception;

public class TwitchSubscriptionAlreadyExistsException extends RuntimeException {

    public TwitchSubscriptionAlreadyExistsException() {
    }

    public TwitchSubscriptionAlreadyExistsException(String message) {
        super(message);
    }

    public TwitchSubscriptionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TwitchSubscriptionAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public TwitchSubscriptionAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
