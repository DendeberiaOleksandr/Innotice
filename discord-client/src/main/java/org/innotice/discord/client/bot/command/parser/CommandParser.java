package org.innotice.discord.client.bot.command.parser;

import org.innotice.discord.client.bot.command.Command;

public interface CommandParser<T extends Command> {
    T parse(String message);
    String commandSignature();
}
