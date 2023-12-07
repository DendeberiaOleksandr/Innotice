package com.innotice.discord.server.resource.server.bot.handler;

import com.innotice.discord.server.resource.server.bot.command.parser.CommandParser;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MessageCreateEventHandler implements EventHandler<MessageCreateEvent> {

    private final Map<Integer, CommandParser> commandParsers;

    public MessageCreateEventHandler(List<CommandParser> commandParsers) {
        this.commandParsers = commandParsers.stream()
                .collect(Collectors.toMap(
                        commandParser -> calculateCommandSignatureHash(commandParser.commandSignature()),
                        commandParser -> commandParser
                ));
    }

    @Override
    public Mono<Void> handle(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        String messageContent = message.getContent();
        CommandParser commandParser = commandParsers.get(calculateCommandSignatureHash(getSignatureFromMessage(messageContent)));
        return commandParser.parse(messageContent).execute();
    }

    @Override
    public Class<? extends Event> getEventType() {
        return MessageCreateEvent.class;
    }

    private String getSignatureFromMessage(String message) {
        return message.split(" ")[0];
    }

    private int calculateCommandSignatureHash(String signature) {
        return signature.hashCode();
    }
}
