package org.innotice.security;

import org.innotice.security.authentication.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    public SecurityConfig(AuthenticationFilter authenticationFilter,
                          @Qualifier("delegatingReactiveAuthenticationManager") ReactiveAuthenticationManager reactiveAuthenticationManager) {
        this.authenticationFilter = authenticationFilter;
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(auth -> auth
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authenticationManager(reactiveAuthenticationManager)
                .addFilterBefore(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
