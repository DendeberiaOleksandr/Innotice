package com.innotice.discord.server.resource.server.bot.command.parser;

import com.innotice.discord.server.resource.server.bot.command.Command;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class AbstractSingleWordSignatureBasedCommandParser<T extends Command> implements CommandParser<T> {

    protected String[] parseCommandData(String command) {
        return (String[]) Arrays.stream(command.split(" ")).skip(1).toArray();
    }

}
