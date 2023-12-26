package org.innotice.messaging.message.stream.twitch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateTwitchStreamIsLiveSubscriptionMessage extends TwitchMessage {
    private boolean isSubscribed;
}
