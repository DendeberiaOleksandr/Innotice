package com.innotice.discord.server.resource.server.service;

import com.innotice.model.domain.discord.server.DiscordServer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface DiscordServerReactiveService {

    Flux<DiscordServer> findAll();
    Flux<DiscordServer> findAllById(Set<Long> ids);
    Mono<DiscordServer> findById(Long id);
    Mono<DiscordServer> save(DiscordServer discordServer);
    Mono<DiscordServer> update(DiscordServer discordServer);
    Mono<Void> deleteAll();
    Mono<Void> deleteById(Long id);
}
