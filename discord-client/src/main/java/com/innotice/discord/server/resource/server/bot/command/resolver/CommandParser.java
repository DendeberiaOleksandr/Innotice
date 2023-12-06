package com.innotice.discord.server.resource.server.bot.command.resolver;

import com.innotice.discord.server.resource.server.bot.command.Command;

public interface CommandParser<T extends Command> {
    T parse(String message);
}
