package com.innotice.discord.server.resource.server.bot.command;

import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface Command {

    Mono<Void> execute();
    void setDiscordServerId(BigInteger discordServerId);
    BigInteger getDiscordServerId();
    void setDiscordChatId(BigInteger discordChatId);
    BigInteger getDiscordChatId();

}
