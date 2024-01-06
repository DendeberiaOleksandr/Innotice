package org.innotice.security.authentication.exchanger;

import org.innotice.security.enums.AuthenticationProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface AuthenticationExchanger {

    Mono<Authentication> exchange(ServerHttpRequest request);
    AuthenticationProvider getAuthenticationProvider();

    default String getAuthorizationValue(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().get(HttpHeaders.AUTHORIZATION))
                .flatMap(values -> values.stream().findFirst())
                .orElse(null);
    }

}
