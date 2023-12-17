package org.innotice.discord.client.bot.handler;

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
public class MessageCreateEventHandler implements EventHandler<MessageCreateEvent> {

    private final Map<String, CommandParser> commandParsers;

    public MessageCreateEventHandler(List<CommandParser> commandParsers) {
        this.commandParsers = commandParsers.stream()
                .collect(Collectors.toMap(
                        CommandParser::commandSignature,
                        commandParser -> commandParser
                ));
    }

    @Override
    public Mono<Void> handle(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        Long channelId = message.getChannelId().asLong();
        Long serverId = message.getGuildId().map(Snowflake::asLong).orElse(null);
        String messageContent = message.getContent();
        CommandParser commandParser = commandParsers.get(getSignatureFromMessage(messageContent));
        Command command = commandParser.parse(messageContent);
        command.setDiscordServerId(serverId);
        command.setDiscordChatId(channelId);
        return command.execute();
    }

    @Override
    public Class<? extends Event> getEventType() {
        return MessageCreateEvent.class;
    }

    private String getSignatureFromMessage(String message) {
        return message.split(" ")[0];
    }

}
