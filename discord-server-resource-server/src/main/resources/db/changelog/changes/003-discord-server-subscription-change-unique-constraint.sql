alter table discord_server_streamer_is_live_subscription drop constraint discord_server_streamer_is_li_discord_server_id_streamer_id_key;
alter table discord_server_streamer_is_live_subscription add constraint discord_server_streamer_is_li_discord_server_id_streamer_id_key unique (streamer_id, discord_server_id, channel_id);