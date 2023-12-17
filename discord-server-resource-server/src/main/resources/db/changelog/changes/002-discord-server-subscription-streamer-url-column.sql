alter table discord_server_streamer_is_live_subscription add column  streamer_url varchar(255);
alter table discord_server_streamer_is_live_subscription add column  channel_id bigint not null;