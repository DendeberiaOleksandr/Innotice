create table if not exists discord_server (
    id bigint primary key not null,
    added_at timestamp not null default now()
);

create table if not exists discord_server_streamer_is_live_subscription (
    discord_server_id bigint not null,
    streamer_id bigint not null,
    channel_name varchar(255) not null,
    unique(discord_server_id, streamer_id)
);