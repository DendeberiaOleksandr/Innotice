package com.innotice.model.domain.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {

    private String id;

    @JsonProperty("broadcaster_user_id")
    private String broadcasterUserId;

    @JsonProperty("broadcaster_user_login")
    private String broadcasterUserLogin;

    @JsonProperty("broadcaster_user_name")
    private String broadcasterUserName;

    private String type;

    @JsonProperty("started_at")
    private LocalDateTime startedAt;

}
