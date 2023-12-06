package com.innotice.discord.server.resource.server.bot.command.resolver;

import com.innotice.discord.server.resource.server.bot.command.CreateStreamIsLiveSubscriptionCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateStreamIsLiveSubscriptionCommandParser extends AbstractRegExpCommandParser<CreateStreamIsLiveSubscriptionCommand> {
    public CreateStreamIsLiveSubscriptionCommandParser(String pattern) {
        super(pattern);
    }

    @Override
    public CreateStreamIsLiveSubscriptionCommand parse(String message) {
        return null;
    }
}
