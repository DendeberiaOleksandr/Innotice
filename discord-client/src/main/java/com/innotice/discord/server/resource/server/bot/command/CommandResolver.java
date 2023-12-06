package com.innotice.discord.server.resource.server.bot.command;

public interface CommandResolver<T> {
    Command<?> resolve(String message);
}
