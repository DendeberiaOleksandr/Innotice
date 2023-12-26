package org.innotice.messaging.message.stream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.innotice.messaging.message.AbstractMessage;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class StreamMessage extends AbstractMessage {

    private Long streamerId;
    private String streamerName;

    public StreamMessage(Long discordServerId, Long discordChannelId,
                         LocalDateTime issuedAt, String id, Long streamerId,
                         String streamerName) {
        super(discordServerId, discordChannelId, issuedAt, id);
        this.streamerId = streamerId;
        this.streamerName = streamerName;
    }
}
