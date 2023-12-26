package org.innotice.kafka.config.topic.streaming.twitch;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTwitchStreamingTopicConfig {

    public static final String TWITCH_STREAMER_IS_LIVE_TOPIC = "twitchStreamerIsLive";
    public static final String CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC = "createTwitchStreamIsLiveSubscription";

    @Bean
    public NewTopic twitchStreamerIsLiveTopic() {
        return new NewTopic(TWITCH_STREAMER_IS_LIVE_TOPIC, 5, (short) 1);
    }

    @Bean
    public NewTopic createTwitchStreamIsLiveSubscription() {
        return new NewTopic(CREATE_TWITCH_STREAM_IS_LIVE_SUBSCRIPTION_TOPIC, 5, (short) 1);
    }

}
