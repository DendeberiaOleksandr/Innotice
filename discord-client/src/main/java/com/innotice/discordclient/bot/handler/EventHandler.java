package com.innotice.discordclient.bot.handler;

import discord4j.core.event.domain.Event;
import reactor.core.publisher.Mono;

public interface EventHandler {
    Mono<Void> handle(Event event);
    Class<? extends Event> getEventType();
}
