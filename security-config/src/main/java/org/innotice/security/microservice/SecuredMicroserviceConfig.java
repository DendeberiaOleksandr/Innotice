//package org.innotice.security.microservice;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@ConditionalOnProperty(value = "org.innotice.security.microservice.secured", havingValue = "true")
//@EnableWebFluxSecurity
//@Configuration
//public class SecuredMicroserviceConfig {
//
//    private final SecretHeaderAuthorizationFilter secretHeaderAuthorizationFilter;
//
//    public SecuredMicroserviceConfig(SecretHeaderAuthorizationFilter secretHeaderAuthorizationFilter) {
//        this.secretHeaderAuthorizationFilter = secretHeaderAuthorizationFilter;
//    }
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().permitAll())
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
//                .addFilterBefore(secretHeaderAuthorizationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();
//    }
//}
