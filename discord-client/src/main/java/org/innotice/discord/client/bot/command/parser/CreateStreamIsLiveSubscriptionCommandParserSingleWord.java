package org.innotice.discord.client.bot.command.parser;

import com.innotice.model.domain.twitch.StreamerData;
import org.innotice.discord.client.bot.command.CreateStreamIsLiveSubscriptionCommand;
import com.innotice.util.NumberUtils;
import com.innotice.util.URLUtils;
import lombok.extern.slf4j.Slf4j;
import org.innotice.messaging.publisher.kafka.streaming.twitch.CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class CreateStreamIsLiveSubscriptionCommandParserSingleWord extends AbstractSingleWordSignatureBasedCommandParser<CreateStreamIsLiveSubscriptionCommand> {

    private final CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher;
    private final WebClient.Builder webClientBuilder;

    public CreateStreamIsLiveSubscriptionCommandParserSingleWord(CreateTwitchStreamIsLiveSubscriptionMessageKafkaPublisher publisher,
                                                                 WebClient.Builder webClientBuilder) {
        this.publisher = publisher;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public CreateStreamIsLiveSubscriptionCommand parse(String message) {
        String commandData = parseCommandData(message);

        CreateStreamIsLiveSubscriptionCommand command = new CreateStreamIsLiveSubscriptionCommand(publisher);

        if (URLUtils.isUrl(commandData)) {
            command.setStreamerId(getStreamerIdByChannelURL(commandData));
        } else if (NumberUtils.isNumeric(commandData)) {
            command.setStreamerId(Long.parseLong(commandData));
        } else {
            command.setStreamerName(commandData);
            command.setStreamerId(getStreamerIdByStreamerName(commandData));
        }

        return command;
    }

    private Long getStreamerIdByStreamerName(String name) {
        StreamerData streamerData = webClientBuilder.baseUrl("http://TWITCH-SERVICE")
                .build()
                .get()
                .uri("/channels/search?query=%s".formatted(name))
                .attribute("query", name)
                .retrieve()
                .bodyToMono(StreamerData.class)
                .block();
        log.info("getStreamerIdByStreamerName: received streamer data by streamer name: {}", streamerData);
        return Long.valueOf(streamerData
                .getId());
    }

    private Long getStreamerIdByChannelURL(String url) {
        String[] split = url.split("/");
        return getStreamerIdByStreamerName(split[split.length - 1]);
    }

    @Override
    public String commandSignature() {
        return "/streamLiveAdd";
    }
}
