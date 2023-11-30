package com.innotice.twitch.webhook.security.auth;

import reactor.core.publisher.Mono;

public interface TwitchAuthorizationService {

    Mono<String> getAccessToken();

}
