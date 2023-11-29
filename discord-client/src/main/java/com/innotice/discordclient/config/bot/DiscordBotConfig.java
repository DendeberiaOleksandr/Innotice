package com.innotice.discordclient.config.bot;

import discord4j.core.DiscordClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBotConfig {

    @Bean
    public DiscordClient discordClient(
            @Value("${org.innotice.client.discord.bot.token}") String token
    ) {
        return DiscordClient.create(token);
    }

}
