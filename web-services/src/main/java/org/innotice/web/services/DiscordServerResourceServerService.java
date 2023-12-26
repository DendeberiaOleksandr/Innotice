package org.innotice.web.services;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.DiscordServerFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DiscordServerResourceServerService {

    Flux<DiscordServer> findAll(DiscordServerFilter filter);
    Mono<DiscordServer> save(DiscordServer discordServer);

}
