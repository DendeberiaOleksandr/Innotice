package org.innotice.discord.client.bot.config;

import discord4j.core.GatewayDiscordClient;
import org.innotice.discord.client.bot.handler.EventHandler;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class DiscordBotConfig {

    private final Map<Class<? extends Event>, EventHandler> eventHandlers;

    public DiscordBotConfig(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers.stream().collect(Collectors.toMap(EventHandler::getEventType, eventHandler -> eventHandler));
    }

    @Bean
    public RestClient restClient(DiscordBotProperties discordBotProperties) {
        return RestClient.create(discordBotProperties.getToken());
    }

    @Bean
    public DiscordClient discordClient(DiscordBotProperties discordBotProperties) {
        DiscordClient discordClient = DiscordClient.create(discordBotProperties.getToken());
        GatewayDiscordClient gateway = discordClient.login().block();
        gateway.on(MessageCreateEvent.class)
                .subscribe(event -> getEventHandler(event.getClass()).handle(event).subscribe());
        return discordClient;
    }

    private EventHandler getEventHandler(Class<? extends Event> eventType) {
        return Optional.ofNullable(eventHandlers.get(eventType))
                .orElseThrow(() -> new UnsupportedOperationException(String.format("Event handler not supported for event type: %s", eventType.getCanonicalName())));
    }

}
