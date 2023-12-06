package com.innotice.discord.server.resource.server.bot.messaging.streaming.twitch;

import com.innotice.messaging.listener.ListenerPostProcessor;
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
