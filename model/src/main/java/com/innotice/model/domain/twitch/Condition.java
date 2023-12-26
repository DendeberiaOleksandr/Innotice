package com.innotice.model.domain.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Condition {

    @JsonProperty("broadcaster_user_id")
    private String broadcasterUserId;

}
