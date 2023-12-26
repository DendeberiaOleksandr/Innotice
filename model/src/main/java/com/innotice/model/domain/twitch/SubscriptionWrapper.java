package com.innotice.model.domain.twitch;

import lombok.Data;

@Data
public class SubscriptionWrapper {

    private Subscription subscription;
    private Event event;

}
