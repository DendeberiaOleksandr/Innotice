package org.innotice.messaging.message.stream.twitch;

import org.innotice.messaging.message.stream.StreamMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class TwitchMessage extends StreamMessage {

}
