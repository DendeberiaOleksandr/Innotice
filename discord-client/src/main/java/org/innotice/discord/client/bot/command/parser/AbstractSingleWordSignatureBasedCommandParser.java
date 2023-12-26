package org.innotice.discord.client.bot.command.parser;

import org.innotice.discord.client.bot.command.Command;

import java.util.Arrays;

public abstract class AbstractSingleWordSignatureBasedCommandParser<T extends Command> implements CommandParser<T> {

    protected String parseCommandData(String command) {
        return command.split(" ")[1];
    }

}
