package org.innotice.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class InternalSecretProperties {

    private String secret;

    @Value("${org.innotice.security.internalSecret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
