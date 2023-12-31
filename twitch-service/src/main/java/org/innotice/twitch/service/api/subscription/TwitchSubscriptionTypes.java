package org.innotice.twitch.service.api.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TwitchSubscriptionTypes {
    STREAM_ONLINE("stream.online");

    private final String name;
}
