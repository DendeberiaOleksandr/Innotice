package org.innotice.twitch.service.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class TwitchAppCredentialsAuthorizationService implements TwitchAuthorizationService {

    private static final String API = "https://id.twitch.tv/oauth2/token";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";

    private final TwitchSecurityCredentials securityCredentials;
    private final WebClient webClient;

    private final AccessTokenStorage accessTokenStorage;

    public TwitchAppCredentialsAuthorizationService(TwitchSecurityCredentials securityCredentials,
                                                    @Qualifier("webClient") WebClient webClient,
                                                    AccessTokenStorage accessTokenStorage) {
        this.securityCredentials = securityCredentials;
        this.webClient = webClient;
        this.accessTokenStorage = accessTokenStorage;
    }


    @Override
    public Mono<String> getAccessToken() {
        if (accessTokenStorage.isTokenPresent()) {
            return Mono.just(accessTokenStorage.getAccessToken().getAccessToken());
        }
        return webClient
                .post()
                .uri("https://id.twitch.tv/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(CLIENT_ID, securityCredentials.getClientId())
                        .with(CLIENT_SECRET, securityCredentials.getClientSecret())
                        .with(GRANT_TYPE, securityCredentials.getGrantType())
                )
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> {
                    long now = System.currentTimeMillis();
                    long expiration = now + ( (int) map.get("expires_in") * 1000L );
                    return new AccessToken(
                            (String) map.get("access_token"),
                            expiration,
                            (String) map.get("token_type"),
                            now
                    );
                })
                .map(token -> {
                    accessTokenStorage.setAccessToken(token);
                    return token.getAccessToken();
                });
    }
}