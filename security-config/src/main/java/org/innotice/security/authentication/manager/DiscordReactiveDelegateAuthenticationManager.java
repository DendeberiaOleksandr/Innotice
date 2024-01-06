package org.innotice.security.authentication.manager;

import com.innotice.model.domain.discord.DiscordUser;
import lombok.RequiredArgsConstructor;
import org.innotice.security.authentication.entity.DiscordAuthentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class DiscordReactiveDelegateAuthenticationManager implements ReactiveDelegateAuthenticationManager {

    private static final String DISCORD_USER_DETAILS_URL = "";

    private final WebClient webClient;

    @Override
    public Class<? extends Authentication> getSupportedAuthentication() {
        return DiscordAuthentication.class;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .filter(this::isSupported)
                .flatMap(auth -> {
                    DiscordAuthentication discordAuthentication = (DiscordAuthentication) auth;
                    return Mono.just(discordAuthentication)
                            .flatMap(discordAuth -> webClient.get()
                                    .uri(DISCORD_USER_DETAILS_URL)
                                    .header(AUTHORIZATION, "Bearer %s".formatted(discordAuthentication.getToken()))
                                    .retrieve()
                                    .bodyToMono(DiscordUser.class)
                                    .onErrorMap(Exception.class, e -> new AccessDeniedException(e.getMessage()))
                                    .map(discordUser -> {
                                        discordAuth.setAuthenticated(true);
                                        discordAuth.setDiscordUser(discordUser);
                                        return discordAuth;
                                    })
                            );
                });
    }
}
