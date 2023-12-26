package com.innotice.model.domain.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Subscription {

    private String id;
    private String status;
    private String type;
    private String version;
    private Condition condition;
    private Transport transport;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    private int cost;

}
