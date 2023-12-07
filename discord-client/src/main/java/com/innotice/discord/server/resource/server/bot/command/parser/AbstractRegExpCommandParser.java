package com.innotice.discord.server.resource.server.bot.command.parser;

import com.innotice.discord.server.resource.server.bot.command.Command;

import java.util.regex.Pattern;

public abstract class AbstractRegExpCommandParser<T extends Command> implements CommandParser<T> {

    private final Pattern pattern;

    public AbstractRegExpCommandParser(Pattern pattern) {
        this.pattern = pattern;
    }

    public AbstractRegExpCommandParser(String pattern) {
        this(Pattern.compile(pattern));
    }
}
