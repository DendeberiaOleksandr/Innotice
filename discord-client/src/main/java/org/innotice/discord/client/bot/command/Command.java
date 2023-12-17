package org.innotice.discord.client.bot.command;

import reactor.core.publisher.Mono;

public interface Command {

    Mono<Void> execute();
    void setDiscordServerId(Long discordServerId);
    Long getDiscordServerId();
    void setDiscordChatId(Long discordChatId);
    Long getDiscordChatId();

}
