package com.innotice.discord.server.resource.server.bot.command;

import reactor.core.publisher.Mono;

public interface Command {

    Mono<Void> execute();

}
