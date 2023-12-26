package org.innotice.discord.client.bot.listener;

import com.innotice.util.TwitchUtils;
import discord4j.common.util.Snowflake;
import discord4j.discordjson.json.EmbedData;
import lombok.extern.slf4j.Slf4j;
import org.innotice.discord.client.bot.sender.DiscordMessageSender;
import org.innotice.messaging.listener.kafka.AbstractKafkaListener;
import org.innotice.messaging.message.stream.twitch.TwitchStreamerIsLiveMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static org.innotice.kafka.config.topic.streaming.twitch.KafkaTwitchStreamingTopicConfig.TWITCH_STREAMER_IS_LIVE_TOPIC;

@Component
@Slf4j
public class TwitchStreamIsLiveMessageListener extends AbstractKafkaListener<TwitchStreamerIsLiveMessage> {

    private final DiscordMessageSender discordMessageSender;

    public TwitchStreamIsLiveMessageListener(DiscordMessageSender discordMessageSender) {
        super(TwitchStreamerIsLiveMessage.class);
        this.discordMessageSender = discordMessageSender;
    }

    @KafkaListener(topics = TWITCH_STREAMER_IS_LIVE_TOPIC, groupId = "discord-client")
    @Override
    public void listen(String message) {
        log.info("listen: {}", message);
        super.convertMessage(message)
                .flatMap(twitchStreamerIsLiveMessage -> discordMessageSender.send(
                        Snowflake.of(twitchStreamerIsLiveMessage.getDiscordChannelId()),
                        "Streamer %s is live!\n%s".formatted(twitchStreamerIsLiveMessage.getStreamerName(), TwitchUtils.generateChannelUrl(twitchStreamerIsLiveMessage.getStreamerName()))
                ))
                .log()
                .subscribe();
    }
}
