package com.innotice.discord.server.resource.server.bot.command;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateStreamIsLiveSubscriptionCommand extends AbstractStreamIsLiveSubscriptionCommand {

    private final WebClient secretWebClient;

    public CreateStreamIsLiveSubscriptionCommand(WebClient secretWebClient) {
        this.secretWebClient = secretWebClient;
    }

    @Override
    public Mono<Void> execute() {
        return secretWebClient.post()
                .uri("DISCORD-SERVER-RESOURCE-SERVER")
                .body(new DiscordServer(getDiscordServerId(), Set.of(new StreamerIsLiveSubscription(getStreamerId(), getStreamerName(), getStreamerUrl(), getDiscordChatId())), LocalDateTime.now()), DiscordServer.class)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .log()
                .then();
    }
}
