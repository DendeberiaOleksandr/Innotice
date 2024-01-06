package org.innotice.security.authentication.manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;

import java.util.List;

@Configuration
public class ReactiveAuthenticationManagerConfig {

    @Primary
    @Bean
    public ReactiveAuthenticationManager delegatingReactiveAuthenticationManager(List<ReactiveAuthenticationManager> reactiveAuthenticationManagers) {
        return new DelegatingReactiveAuthenticationManager(reactiveAuthenticationManagers);
    }

}
