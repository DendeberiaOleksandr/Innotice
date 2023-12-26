package org.innotice.discord.server.resource.service;

import com.innotice.model.domain.discord.server.DiscordServerFilter;
import org.innotice.discord.server.resource.dao.DiscordServerRepository;
import com.innotice.model.domain.discord.server.DiscordServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@Slf4j
public class DiscordServerReactiveServiceImpl implements DiscordServerReactiveService {

    private final DiscordServerRepository discordServerRepository;

    public DiscordServerReactiveServiceImpl(DiscordServerRepository discordServerRepository) {
        this.discordServerRepository = discordServerRepository;
    }

    @Override
    public Flux<DiscordServer> findAll() {
        return discordServerRepository.findAll();
    }

    @Override
    public Flux<DiscordServer> findAll(DiscordServerFilter filter) {
        return discordServerRepository.findAll(filter);
    }

    @Override
    public Flux<DiscordServer> findAllById(Set<Long> ids) {
        return discordServerRepository.findAllById(ids);
    }

    @Override
    public Mono<DiscordServer> findById(Long id) {
        return discordServerRepository.findById(id);
    }

    @Override
    public Mono<DiscordServer> save(DiscordServer discordServer) {
        log.info("save: {}", discordServer);
        return discordServerRepository.save(discordServer);
    }

    @Override
    public Mono<DiscordServer> upsert(DiscordServer discordServer) {
        log.info("upsert: {}", discordServer);
        return discordServerRepository.upsert(discordServer);
    }

    @Override
    public Mono<DiscordServer> update(DiscordServer discordServer) {
        return discordServerRepository.update(discordServer.getId(), discordServer);
    }

    @Override
    public Mono<Void> deleteAll() {
        return discordServerRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return discordServerRepository.delete(id);
    }

    @Override
    public Mono<Boolean> isSubscriptionExist(Long discordServerId, Long discordChannelId, Long streamerId) {
        return findAll(new DiscordServerFilter(discordServerId, null, Set.of(discordChannelId), Set.of(streamerId)))
                .hasElements();
    }
}
