package org.innotice.discord.server.resource.service;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.DiscordServerFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface DiscordServerReactiveService {

    Flux<DiscordServer> findAll();
    Flux<DiscordServer> findAll(DiscordServerFilter filter);
    Flux<DiscordServer> findAllById(Set<Long> ids);
    Mono<DiscordServer> findById(Long id);
    Mono<DiscordServer> save(DiscordServer discordServer);
    Mono<DiscordServer> upsert(DiscordServer discordServer);
    Mono<DiscordServer> update(DiscordServer discordServer);
    Mono<Void> deleteAll();
    Mono<Void> deleteById(Long id);
    Mono<Boolean> isSubscriptionExist(Long discordServerId, Long discordChannelId, Long streamerId);
}
