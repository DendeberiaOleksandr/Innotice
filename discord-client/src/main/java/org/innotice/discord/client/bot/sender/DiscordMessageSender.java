package org.innotice.discord.client.bot.sender;

import discord4j.common.util.Snowflake;
import reactor.core.publisher.Mono;

public interface DiscordMessageSender {

    Mono<Void> send(Snowflake channelId, String message);

}
