package com.innotice.model.messaging;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AbstractMessage implements Message {
    private LocalDateTime issuedAt;
    private String id;
}
