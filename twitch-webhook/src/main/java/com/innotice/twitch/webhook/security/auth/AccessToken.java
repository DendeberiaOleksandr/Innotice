package com.innotice.twitch.webhook.security.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AccessToken {
    private String accessToken;
    long expiresIn;
    String tokenType;

    @JsonIgnore
    long issuedAt;
}
