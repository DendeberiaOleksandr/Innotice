package org.innotice.twitch.service.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class TwitchSecurityCredentials {

    @Value("${org.innotice.twitch.auth.client.id}")
    private String clientId;

    @Value("${org.innotice.twitch.auth.client.secret}")
    private String clientSecret;

    @Value("${org.innotice.twitch.auth.grant-type:client_credentials}")
    private String grantType;

}