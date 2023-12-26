package org.innotice.web.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.DiscordServerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.util.StreamUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiscordServerResourceServerServiceImpl implements DiscordServerResourceServerService {

    private static final String BASE_URL = "http://DISCORD-SERVER-RESOURCE-SERVER";
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<DiscordServer> findAll(DiscordServerFilter filter) {
        return webClientBuilder
                .baseUrl(BASE_URL)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParams(objectMapper.convertValue(filter, new TypeReference<LinkedMultiValueMap<String, String>>() {}))
                        .build()
                )
                .retrieve()
                .bodyToFlux(DiscordServer.class);
    }

    @Override
    public Mono<DiscordServer> save(DiscordServer discordServer) {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .post()
                .body(discordServer, DiscordServer.class)
                .retrieve()
                .bodyToMono(DiscordServer.class);
    }
}
