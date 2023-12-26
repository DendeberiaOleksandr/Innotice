package org.innotice.messaging.message;
import java.time.LocalDateTime;

public interface Message {

    LocalDateTime getIssuedAt();
    String getId();

}
