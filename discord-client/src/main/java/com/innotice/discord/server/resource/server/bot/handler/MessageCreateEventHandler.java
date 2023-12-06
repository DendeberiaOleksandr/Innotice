package com.innotice.discord.server.resource.server.bot.handler;

import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MessageCreateEventHandler implements EventHandler {

    @Override
    public Mono<Void> handle(Event messageCreateEvent) {
        return Mono.empty();
    }

    @Override
    public Class<? extends Event> getEventType() {
        return MessageCreateEvent.class;
    }
}
