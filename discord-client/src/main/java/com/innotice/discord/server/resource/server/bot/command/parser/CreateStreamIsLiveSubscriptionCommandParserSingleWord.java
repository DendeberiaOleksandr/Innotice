package com.innotice.discord.server.resource.server.bot.command.parser;

import com.innotice.discord.server.resource.server.bot.command.CreateStreamIsLiveSubscriptionCommand;
import com.innotice.util.NumberUtils;
import com.innotice.util.URLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class CreateStreamIsLiveSubscriptionCommandParserSingleWord extends AbstractSingleWordSignatureBasedCommandParser<CreateStreamIsLiveSubscriptionCommand> {

    private final WebClient webClient;

    public CreateStreamIsLiveSubscriptionCommandParserSingleWord(@Qualifier("secretWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public CreateStreamIsLiveSubscriptionCommand parse(String message) {
        String commandData = parseCommandData(message)[0];

        CreateStreamIsLiveSubscriptionCommand command = new CreateStreamIsLiveSubscriptionCommand(webClient);

        if (URLUtils.isUrl(commandData)) {
            command.setStreamerUrl(commandData);
        } else if (NumberUtils.isNumeric(commandData)) {
            command.setStreamerId(Long.parseLong(commandData));
        } else {
            command.setStreamerName(commandData);
        }

        return command;
    }

    @Override
    public String commandSignature() {
        return "/streamLiveAdd";
    }
}
