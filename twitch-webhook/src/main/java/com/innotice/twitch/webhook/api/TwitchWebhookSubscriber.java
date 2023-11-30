package com.innotice.twitch.webhook.api;

import com.innotice.twitch.webhook.security.auth.TwitchAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class TwitchWebhookSubscriber {

    private final WebClient webClient;
    private final TwitchAuthorizationService twitchAuthorizationService;

    public TwitchWebhookSubscriber(WebClient webClient,
                                   TwitchAuthorizationService twitchAuthorizationService) {
        this.webClient = webClient;
        this.twitchAuthorizationService = twitchAuthorizationService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {

    }

}
