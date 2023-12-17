package com.innotice.model.domain.discord.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StreamerIsLiveSubscription {

    private Long streamerId;
    private String channelName;
    private Long discordChatId;

}
