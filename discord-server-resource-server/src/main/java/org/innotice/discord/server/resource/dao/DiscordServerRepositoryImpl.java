package org.innotice.discord.server.resource.dao;

import com.innotice.model.domain.discord.server.DiscordServer;
import com.innotice.model.domain.discord.server.StreamerIsLiveSubscription;
import io.r2dbc.spi.Row;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.odendeberia.dao.AbstractRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class DiscordServerRepositoryImpl extends AbstractRepository<Long, DiscordServer> implements DiscordServerRepository {

    public DiscordServerRepositoryImpl(DatabaseClient databaseClient) {
        super(databaseClient);
    }

    @Override
    public Flux<DiscordServer> findAll() {
        return findAll(null);
    }

    @Override
    public Flux<DiscordServer> findAllById(Set<? extends Long> set) {
        return findAll();
    }

    private Flux<DiscordServer> findAll(DiscordServerFilter filter) {
        StringBuilder sql = new StringBuilder("""
                        select id, added_at, streamer_id, channel_name, streamer_url, channel_id
                        from discord_server ds
                        left join discord_server_streamer_is_live_subscription ds_is_live_subscription
                        on ds.id = ds_is_live_subscription.discord_server_id
                        """);
        DatabaseClient.GenericExecuteSpec executeSpec = executeSpecFilledByArgs(sql, filter);
        return executeSpec.map((row, rowMetadata) -> new DiscordServerRow(row))
                .all()
                .collectList()
                .map(this::collectDiscordServers)
                .flatMapMany(discordServers -> Flux.fromStream(discordServers.stream()));
    }

    private Set<DiscordServer> collectDiscordServers(List<DiscordServerRow> rows) {
        Map<Long, List<DiscordServerRow>> discordServers = rows.stream()
                .collect(Collectors.groupingBy(DiscordServerRow::getId));
        return discordServers.entrySet().stream()
                .map(entry -> new DiscordServer(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(discordServerRow -> new StreamerIsLiveSubscription(
                                        discordServerRow.getStreamerId(), discordServerRow.getStreamerName(),
                                        discordServerRow.getChannelId()
                                ))
                                .collect(Collectors.toSet()),
                        entry.getValue().get(0).getAddedAt()
                ))
                .collect(Collectors.toSet());
    }

    @Override
    public Mono<DiscordServer> findById(Long id) {
        return findAll(DiscordServerFilter.builder().id(id).build()).single();
    }

    @Override
    public Mono<DiscordServer> save(DiscordServer discordServer) {
        return databaseClient.sql("insert into discord_server(id) values (:id)")
                .bind("id", discordServer.getId())
                .fetch()
                .all()
                .flatMap(stringObjectMap -> Flux.fromStream(discordServer.getStreamerIsLiveSubscriptions().stream()))
                .flatMap(streamerIsLiveSubscription -> saveStreamerIsLiveSubscription(discordServer, streamerIsLiveSubscription))
                .single();
    }

    @Override
    public Mono<Void> delete(Long id) {
        return deleteById(List.of(
                new DeleteByIdParam("discord_server_streamer_is_live_subscription", "discord_server_id", id),
                new DeleteByIdParam("discord_server", "id", id)
        ));
    }

    @Override
    public Mono<Void> delete(DiscordServer discordServer) {
        return delete(discordServer.getId());
    }

    @Override
    public Mono<DiscordServer> update(Long id, DiscordServer discordServer) {
        return deleteStreamerIsLiveSubscriptions(discordServer)
                .flatMap(v -> saveStreamerIsLiveSubscriptions(discordServer, discordServer.getStreamerIsLiveSubscriptions()));
    }

    @Override
    public Mono<Void> deleteAll() {
        return deleteAll(List.of("discord_server_streamer_is_live_subscription", "discord_server"));
    }

    private Mono<DiscordServer> saveStreamerIsLiveSubscriptions(DiscordServer discordServer, Set<StreamerIsLiveSubscription> subscriptions) {
        return Flux.fromStream(subscriptions.stream())
                .flatMap(subscription -> saveStreamerIsLiveSubscription(discordServer, subscription))
                .then(Mono.just(discordServer));
    }

    private Mono<DiscordServer> saveStreamerIsLiveSubscription(DiscordServer discordServer, StreamerIsLiveSubscription streamerIsLiveSubscription) {
        return databaseClient.sql("""
                        insert into discord_server_streamer_is_live_subscription 
                        (discord_server_id, streamer_id, channel_name, channel_id)
                        values
                        (:discordServerId, :streamerId, :channelName, :channelId)
                        """)
                .bind("discordServerId", discordServer.getId())
                .bind("streamerId", streamerIsLiveSubscription.getStreamerId())
                .bind("channelName", streamerIsLiveSubscription.getChannelName())
                .bind("channelId", streamerIsLiveSubscription.getDiscordChatId())
                .fetch()
                .all()
                .then(Mono.just(discordServer));
    }

    private Mono<Void> deleteStreamerIsLiveSubscriptions(DiscordServer discordServer) {
        return unlinkNestedEntities("discord_server_streamer_is_live_subscription", "discord_server_id", discordServer.getId()).then();
    }

    private Mono<Void> deleteStreamerIsLiveSubscriptions(DiscordServer discordServer, Set<Long> streamersIds) {
        return unlinkNestedEntities("discord_server_streamer_is_live_subscription",
                discordServer.getId(), streamersIds,
                "discord_server_id", "streamer_id")
                .then();
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class DiscordServerRow {
        private Long id;
        private LocalDateTime addedAt;
        private Long streamerId;
        private String streamerName;
        private Long channelId;

        private DiscordServerRow(Row row) {
            this.id = row.get("id", Long.class);
            this.addedAt = row.get("added_at", LocalDateTime.class);
            this.streamerId = row.get("streamer_id", Long.class);
            this.streamerName = row.get("channel_name", String.class);
            this.channelId = row.get("channel_id", Long.class);
        }
    }
}
