package org.innotice.discord.client.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DiscordBotProperties {

    private String token;

    @Value("${org.innotice.client.discord.bot.token}")
    public void setToken(String token) {
        this.token = token;
    }
}
