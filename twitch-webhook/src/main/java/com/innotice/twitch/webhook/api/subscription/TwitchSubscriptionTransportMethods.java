package com.innotice.twitch.webhook.api.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TwitchSubscriptionTransportMethods {
    WEBHOOK("webhook");

    private final String name;
}
