package com.innotice.twitch.webhook.api.subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TwitchSubscriptionRequest {

    private String type;
    private String version = "2";
    private Map<String, String> condition;
    private Map<String, String> transport;
}
