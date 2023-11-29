package com.innotice.discordclient.bot.config;

import com.innotice.discordclient.bot.handler.EventHandler;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
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
        return DiscordClient.create(discordBotProperties.getToken());
    }

    @Autowired
    public void configGateway(DiscordClient discordClient) {
        discordClient.withGateway(gatewayDiscordClient ->
                gatewayDiscordClient.on(
                       MessageCreateEvent.class, event -> getEventHandler(event.getClass()).handle(event)
                )
        ).block();
    }

    private EventHandler getEventHandler(Class<? extends Event> eventType) {
        return Optional.ofNullable(eventHandlers.get(eventType))
                .orElseThrow(() -> new UnsupportedOperationException(String.format("Event handler not supported for event type: %s", eventType.getCanonicalName())));
    }

}
