package com.innotice.discordclient.bot.command;

import java.util.regex.Pattern;

public enum CommandMatch {
    ;

    private final String command;
    private final Pattern pattern;

    CommandMatch(String command) {
        this.command = command;
        this.pattern = null;
    }

    CommandMatch(String command, String regex) {
        this.command = command;
        this.pattern = Pattern.compile(regex);
    }

    CommandMatch(String command, Pattern pattern) {
        this.command = command;
        this.pattern = pattern;
    }

    CommandMatch(Pattern pattern) {
        this.command = null;
        this.pattern = pattern;
    }
}
