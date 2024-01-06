package org.innotice.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum AuthenticationProvider {

    DISCORD("discord"), INTERNAL("internal");

    private final String name;

    public static AuthenticationProvider getByName(String name) {
        return Arrays.stream(AuthenticationProvider.values())
                .filter(authenticationProvider -> authenticationProvider.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Authentication provider %s not found!".formatted(name)));
    }

}
