package com.innotice.model.messaging;
import java.time.LocalDateTime;

public interface Message {

    LocalDateTime getIssuedAt();
    String getId();

}
