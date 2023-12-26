package org.innotice.discord.client.bot.handler;

import lombok.extern.slf4j.Slf4j;
import org.innotice.discord.client.bot.command.parser.CommandParser;
import org.innotice.discord.client.bot.command.Command;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MessageCreateEventHandler implements EventHandler<MessageCreateEvent> {

    private final Map<String, CommandParser> commandParsers;

    public MessageCreateEventHandler(List<CommandParser> commandParsers) {
        this.commandParsers = commandParsers.stream()
                .peek(commandParser -> log.info("Registering {} command parser", commandParser.getClass()))
                .collect(Collectors.toMap(
                        CommandParser::commandSignature,
                        commandParser -> commandParser
                ));
        log.info("Registered {} command parsers", commandParsers.size());
    }

    @Override
    public Mono<Void> handle(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        Long channelId = message.getChannelId().asLong();
        Long serverId = message.getGuildId().map(Snowflake::asLong).orElse(null);
        String messageContent = message.getContent();
        log.info("Received message: {}. From server: {}, channel: {}", message, serverId, channelId);
        CommandParser commandParser = commandParsers.get(getSignatureFromMessage(messageContent));
        if (commandParser != null) {
            Command command = commandParser.parse(messageContent);
            command.setDiscordServerId(serverId);
            command.setDiscordChatId(channelId);
            return command.execute();
        }
        return Mono.just("There is no command parser for provided message: %s".formatted(messageContent)).log().then();
    }

    @Override
    public Class<? extends Event> getEventType() {
        return MessageCreateEvent.class;
    }

    private String getSignatureFromMessage(String message) {
        return message.split(" ")[0];
    }

}
