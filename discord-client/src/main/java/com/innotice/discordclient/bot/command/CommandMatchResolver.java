package com.innotice.discordclient.bot.command;

public interface CommandMatchResolver {
    CommandMatch resolve(String message);
}
