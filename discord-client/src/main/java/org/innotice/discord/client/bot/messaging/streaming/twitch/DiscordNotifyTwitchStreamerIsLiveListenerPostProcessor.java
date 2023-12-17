package org.innotice.discord.client.bot.messaging.streaming.twitch;

import org.innotice.messaging.listener.ListenerPostProcessor;
import com.innotice.model.messaging.stream.twitch.TwitchStreamerIsLiveMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DiscordNotifyTwitchStreamerIsLiveListenerPostProcessor implements ListenerPostProcessor<TwitchStreamerIsLiveMessage> {

    @Override
    public Mono<Void> process(Mono<TwitchStreamerIsLiveMessage> twitchStreamerIsLiveMessage) {
        return Mono.empty();
    }
}
