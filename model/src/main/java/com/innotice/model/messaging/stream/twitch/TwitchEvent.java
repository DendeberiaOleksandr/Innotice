package com.innotice.model.messaging.stream.twitch;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TwitchEvent {
    private String userId;
    private String userLogin;
    private String userName;
    private String broadcasterUserId;
    private String broadcasterUserLogin;
    private String broadcasterUserName;
    private LocalDateTime followedAt;
}
