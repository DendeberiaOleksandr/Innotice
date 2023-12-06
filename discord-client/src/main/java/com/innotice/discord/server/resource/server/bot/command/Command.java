package com.innotice.discord.server.resource.server.bot.command;

import lombok.Data;

@Data
public class Command<T> {
    private T data;
    private CommandMatch commandMatch;
}
