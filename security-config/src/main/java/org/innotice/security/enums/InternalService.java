package org.innotice.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum InternalService {
    DISCORD_CLIENT("discord-client"), DISCORD_SERVER_RESOURCE_SERVER("discord-server-resource-server"),
    TWITCH_SERVICE("twitch-service"), SECURITY_TEST("security-test");

    private final String name;

    public static InternalService getByName(String name) {
        return Arrays.stream(InternalService.values())
                .filter(internalService -> internalService.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Internal service does not exist %s".formatted(name)));
    }
}
