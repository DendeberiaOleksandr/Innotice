package com.innotice.discord.server.resource.server.bot.handler;

import discord4j.core.event.domain.Event;
import reactor.core.publisher.Mono;

public interface EventHandler<T extends Event> {
    Mono<Void> handle(T t);
    Class<? extends Event> getEventType();
}
