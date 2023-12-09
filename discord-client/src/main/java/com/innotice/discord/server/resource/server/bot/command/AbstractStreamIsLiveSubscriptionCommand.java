package com.innotice.discord.server.resource.server.bot.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractStreamIsLiveSubscriptionCommand implements Command {

    private BigInteger discordServerId;
    private BigInteger streamerId;
    private String streamerName;
    private String streamerUrl;
    private Long discordChatId;

}
