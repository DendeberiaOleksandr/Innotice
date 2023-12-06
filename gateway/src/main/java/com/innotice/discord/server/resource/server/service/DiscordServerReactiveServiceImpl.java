package com.innotice.discord.server.resource.server.service;

import com.innotice.discord.server.resource.server.dao.DiscordServerRepository;
import com.innotice.model.domain.discord.server.DiscordServer;
import lombok.extern.slf4j.Slf4j;
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
    public Flux<DiscordServer> findAllById(Set<Long> ids) {
        return discordServerRepository.findAllById(ids);
    }

    @Override
    public Mono<DiscordServer> findById(Long id) {
        return discordServerRepository.findById(id);
    }

    @Override
    public Mono<DiscordServer> save(DiscordServer discordServer) {
        return discordServerRepository.save(discordServer);
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
}
