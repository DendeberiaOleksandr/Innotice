package org.innotice.twitch.service.web;

import com.innotice.model.domain.discord.server.DiscordServer;
import reactor.core.publisher.Flux;

public interface DiscordServerResourceServerService {

    Flux<DiscordServer> findAll();

}
