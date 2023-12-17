//package org.innotice.security.microservice;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//
//@Component
//@RequiredArgsConstructor
//public class SecretHeaderAuthorizationFilter implements WebFilter {
//
//    private final SecretHeaderProperties secretHeaderProperties;
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//    private String[] permittedPaths;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        if (isHeaderRequired(request)) {
//            String headerName = secretHeaderProperties.getHeaderName();
//            String secretHeader = request.getHeaders().getFirst(headerName);
//            if (!secretHeaderProperties.getSecret().equals(secretHeader)) {
//                return Mono.error(new AccessDeniedException(String.format("Provided invalid %s header. Access is denied.", headerName)));
//            }
//        }
//        return chain.filter(exchange);
//    }
//
//    private boolean isHeaderRequired(ServerHttpRequest request) {
//        return Arrays.stream(permittedPaths).noneMatch(path -> antPathMatcher.match(path, request.getPath().value()));
//    }
//
//    @Value("${org.innotice.security.microservice.permittedPaths:/actuator/**}")
//    public void setPermittedPaths(String[] permittedPaths) {
//        this.permittedPaths = permittedPaths;
//    }
//}
