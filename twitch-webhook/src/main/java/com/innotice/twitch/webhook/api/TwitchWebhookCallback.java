package com.innotice.twitch.webhook.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/webhooks/callback")
public class TwitchWebhookCallback {

    @PostMapping
    public Mono<ResponseEntity<?>> callback() {
        log.info("Received callback");
        return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
    }

}
