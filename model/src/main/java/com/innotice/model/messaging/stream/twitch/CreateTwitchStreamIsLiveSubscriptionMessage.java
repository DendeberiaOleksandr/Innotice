package com.innotice.model.messaging.stream.twitch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateTwitchStreamIsLiveSubscriptionMessage extends TwitchMessage {

    private Long streamerId;

}
