package org.innotice.discord.client.bot.sender;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.discordjson.json.EmbedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiscordMessageSenderImpl implements DiscordMessageSender {

    private final DiscordClient discordClient;

    @Override
    public Mono<Void> send(Snowflake channelId, String message) {
        return discordClient.getChannelById(channelId)
                .createMessage(message)
                .log()
                .then();
    }
}
