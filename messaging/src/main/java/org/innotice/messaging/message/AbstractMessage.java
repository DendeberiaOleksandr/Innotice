package org.innotice.messaging.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractMessage implements Message {
    private Long discordServerId;
    private Long discordChannelId;
    private LocalDateTime issuedAt;
    private String id;
}
