package org.innotice.discord.server.resource.dao;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.DiscordServerFilter;
import org.odendeberia.dao.ReactiveRepository;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DiscordServerRepository extends ReactiveRepository<Long, DiscordServer> {

    Flux<DiscordServer> findAll(DiscordServerFilter filter);
    Mono<DiscordServer> upsert(DiscordServer discordServer);

}
