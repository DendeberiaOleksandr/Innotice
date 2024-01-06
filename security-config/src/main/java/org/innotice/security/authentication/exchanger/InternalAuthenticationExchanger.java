package org.innotice.security.authentication.exchanger;

import org.innotice.security.authentication.entity.InternalAuthentication;
import org.innotice.security.enums.AuthenticationProvider;
import org.innotice.security.enums.InternalService;
import org.innotice.security.util.SecurityUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class InternalAuthenticationExchanger implements AuthenticationExchanger {

    @Override
    public Mono<Authentication> exchange(ServerHttpRequest request) {
        return Mono.just(getAuthorizationValue(request))
                .map(token -> new InternalAuthentication(
                        getInternalService(request),
                        token,
                        false
                ));
    }

    private InternalService getInternalService(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().get(SecurityUtils.INTERNAL_SERVICE_HEADER))
                .flatMap(values -> values.stream().findFirst())
                .map(InternalService::getByName)
                .orElseThrow(() -> new IllegalArgumentException("Internal service does not exist"));
    }

    @Override
    public AuthenticationProvider getAuthenticationProvider() {
        return AuthenticationProvider.INTERNAL;
    }
}
