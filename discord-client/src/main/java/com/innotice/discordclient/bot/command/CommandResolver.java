package com.innotice.discordclient.bot.command;

public interface CommandResolver<T> {
    Command<> resolve(String message);
}
