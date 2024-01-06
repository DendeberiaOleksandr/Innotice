package org.innotice.security.authentication.exchanger;

import lombok.RequiredArgsConstructor;
import org.innotice.security.authentication.entity.DiscordAuthentication;
import org.innotice.security.enums.AuthenticationProvider;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiscordAuthenticationExchanger implements AuthenticationExchanger {

    @Override
    public Mono<Authentication> exchange(ServerHttpRequest request) {
        return Mono.just(getAuthorizationValue(request))
                .map(token -> new DiscordAuthentication(token, null, false));
    }

    @Override
    public AuthenticationProvider getAuthenticationProvider() {
        return AuthenticationProvider.DISCORD;
    }
}
