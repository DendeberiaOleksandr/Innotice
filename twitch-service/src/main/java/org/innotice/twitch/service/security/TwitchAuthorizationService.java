package org.innotice.twitch.service.security;

import reactor.core.publisher.Mono;

public interface TwitchAuthorizationService {

    Mono<String> getAccessToken();

}