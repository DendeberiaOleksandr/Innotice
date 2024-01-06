package org.innotice.security.authentication.exchanger;

import org.innotice.security.enums.AuthenticationProvider;
import org.innotice.security.util.SecurityUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationExchangeManager {

    private final Map<AuthenticationProvider, AuthenticationExchanger> exchangerMap;

    public AuthenticationExchangeManager(List<AuthenticationExchanger> exchangers) {
        this.exchangerMap = exchangers.stream().collect(Collectors.toMap(
                AuthenticationExchanger::getAuthenticationProvider,
                authenticationExchanger -> authenticationExchanger
        ));
    }

    public Mono<Authentication> exchange(ServerHttpRequest request) {
        return Optional.ofNullable(parseFromRequest(request))
                .map(exchangerMap::get)
                .map(authenticationExchanger -> authenticationExchanger.exchange(request))
                .orElse(null);
    }

    private AuthenticationProvider parseFromRequest(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().get(SecurityUtils.AUTH_PROVIDER_HEADER))
                .flatMap(authProviders -> authProviders.stream().findFirst())
                .map(AuthenticationProvider::getByName)
                .orElse(null);
    }
}
