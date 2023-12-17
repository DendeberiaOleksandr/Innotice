package org.innotice.discord.client.bot.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractStreamIsLiveSubscriptionCommand implements Command {

    private Long discordServerId;
    private Long streamerId;
    private String streamerName;
    private String streamerUrl;
    private Long discordChatId;

}
