package org.innotice.security.authentication.manager;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;

public interface ReactiveDelegateAuthenticationManager extends ReactiveAuthenticationManager {

    Class<? extends Authentication> getSupportedAuthentication();

    default boolean isSupported(Authentication authentication) {
        return getSupportedAuthentication().isAssignableFrom(authentication.getClass());
    }

}
