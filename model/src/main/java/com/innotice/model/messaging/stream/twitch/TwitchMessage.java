package com.innotice.model.messaging.stream.twitch;

import com.innotice.model.messaging.stream.StreamMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class TwitchMessage extends StreamMessage {

    private String status;
    private String type;
    private Integer cost;
    private String version;
    private LocalDateTime createdAt;
    private TwitchEvent event;

}
