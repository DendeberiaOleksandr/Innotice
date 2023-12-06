package com.innotice.model.domain.discord.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.odendeberia.domain.Entity;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiscordServer implements Entity<Long> {

    private Long id;
    private Set<StreamerIsLiveSubscription> streamerIsLiveSubscriptions;
    private LocalDateTime addedAt;

}
