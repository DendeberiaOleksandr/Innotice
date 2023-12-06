package com.innotice.security.microservice;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecretHeaderProperties {

    private String headerName;
    private String secret;

    @Value("${org.innotice.security.secretHeader.name:x-innotice-secret}")
    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    @Value("${org.innotice.security.secretHeader}")
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
