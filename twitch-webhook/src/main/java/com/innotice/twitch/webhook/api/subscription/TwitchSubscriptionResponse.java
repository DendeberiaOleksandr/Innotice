package com.innotice.twitch.webhook.api.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitchSubscriptionResponse {

    private List<ResponseData> data;
    private int total;
    private int totalCost;
    private long maxTotalCost;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ResponseData {
        private String id;
        private String status;
        private String type;
        private String version;
        private int cost;
        private Map<String, String> condition;
        private Map<String, String> transport;
        private String createdAt;
    }
}
