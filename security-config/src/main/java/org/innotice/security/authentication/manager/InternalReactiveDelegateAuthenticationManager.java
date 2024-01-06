package org.innotice.security.authentication.manager;

import lombok.RequiredArgsConstructor;
import org.innotice.security.InternalSecretProperties;
import org.innotice.security.authentication.entity.InternalAuthentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InternalReactiveDelegateAuthenticationManager implements ReactiveDelegateAuthenticationManager {

    private final InternalSecretProperties secretProperties;

    @Override
    public Class<? extends Authentication> getSupportedAuthentication() {
        return InternalAuthentication.class;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .filter(this::isSupported)
                .map(auth -> {
                    InternalAuthentication internalAuthentication = (InternalAuthentication) auth;
                    if (!secretProperties.getSecret().equals(internalAuthentication.getSecret())) {
                        throw new AccessDeniedException("Invalid secret provided. Access is denied for %s".formatted(internalAuthentication.getInternalService().getName()));
                    }
                    internalAuthentication.setAuthenticated(true);
                    return internalAuthentication;
                });
    }
}
