package org.innotice.discord.client.bot.command;

import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import org.innotice.messaging.message.stream.twitch.CreateTwitchStreamIsLiveSubscriptionMessage;
import org.innotice.messaging.publisher.kafka.streaming.twitch.CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class CreateStreamIsLiveSubscriptionCommand extends AbstractStreamIsLiveSubscriptionCommand {

    private final CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher;

    public CreateStreamIsLiveSubscriptionCommand(CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public Mono<Void> execute() {
        CreateTwitchStreamIsLiveSubscriptionMessage message = new CreateTwitchStreamIsLiveSubscriptionMessage();
        message.setIssuedAt(LocalDateTime.now());
        message.setDiscordServerId(getDiscordServerId());
        message.setSubscribed(false);
        message.setDiscordChannelId(getDiscordChatId());
        message.setStreamerId(getStreamerId());
        message.setStreamerName(getStreamerName());
        return publisher.publish(message).then();
    }
}
