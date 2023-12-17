package org.innotice.twitch.service.web;

import com.innotice.model.domain.discord.server.DiscordServer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class DiscordServerResourceServerServiceImpl implements DiscordServerResourceServerService {

    private final WebClient.Builder webClientBuilder;

    public DiscordServerResourceServerServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Flux<DiscordServer> findAll() {
        return webClientBuilder.build()
                .get()
                .uri("http://DISCORD-SERVER-RESOURCE-SERVER")
                .retrieve()
                .bodyToFlux(DiscordServer.class);
    }
}
