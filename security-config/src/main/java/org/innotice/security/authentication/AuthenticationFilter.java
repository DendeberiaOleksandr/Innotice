package org.innotice.security.authentication;

import org.innotice.security.authentication.exchanger.AuthenticationExchangeManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements WebFilter {

    private final AuthenticationExchangeManager authenticationExchangeManager;
    private final ReactiveAuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationExchangeManager authenticationExchangeManager,
                                @Qualifier("delegatingReactiveAuthenticationManager") ReactiveAuthenticationManager authenticationManager) {
        this.authenticationExchangeManager = authenticationExchangeManager;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return Mono.just(exchange.getRequest())
                .flatMap(authenticationExchangeManager::exchange)
                .flatMap(authenticationManager::authenticate)
                .onErrorResume(Exception.class, e -> Mono.empty())
                .then(chain.filter(exchange));
    }
}
