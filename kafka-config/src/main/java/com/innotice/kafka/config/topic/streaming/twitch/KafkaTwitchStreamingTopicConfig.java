package com.innotice.kafka.config.topic.streaming.twitch;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTwitchStreamingTopicConfig {

    public static final String TWITCH_STREAMER_IS_LIVE_TOPIC = "twitchStreamerIsLive";

    public NewTopic twitchStreamerIsLiveTopic() {
        return new NewTopic(TWITCH_STREAMER_IS_LIVE_TOPIC, 1, (short) 1);
    }

}
