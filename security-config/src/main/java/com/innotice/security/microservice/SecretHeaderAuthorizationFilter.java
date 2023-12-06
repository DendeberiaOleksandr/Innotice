package com.innotice.security.microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SecretHeaderAuthorizationFilter implements WebFilter {

    private final SecretHeaderProperties secretHeaderProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String headerName = secretHeaderProperties.getHeaderName();
        String secretHeader = request.getHeaders().getFirst(headerName);
        if (!secretHeaderProperties.getSecret().equals(secretHeader)) {
            return Mono.error(new AccessDeniedException(String.format("Provided invalid %s header. Access is denied.", headerName)));
        }
        return chain.filter(exchange);
    }
}
