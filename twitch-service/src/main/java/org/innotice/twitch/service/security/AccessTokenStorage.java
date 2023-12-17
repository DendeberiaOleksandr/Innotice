package org.innotice.twitch.service.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class AccessTokenStorage {

    private AccessToken accessToken;

    public boolean isTokenPresent() {
        return accessToken != null && !isTokenExpired();
    }

    private boolean isTokenExpired() {
        return accessToken.getIssuedAt() + accessToken.expiresIn * 1000 <= System.currentTimeMillis();
    }

}