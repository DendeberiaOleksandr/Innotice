package com.innotice.model.domain.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamerData {

    @JsonProperty("broadcaster_language")
    private String broadcasterLanguage;
    @JsonProperty("broadcaster_login")
    private String broadcasterLogin;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("game_id")
    private String gameId;
    @JsonProperty("game_name")
    private String gameName;
    private String id;
    @JsonProperty("is_live")
    private boolean isLive;
    @JsonProperty("tag_ids")
    private List<String> tagIds;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    private String title;
    @JsonProperty("started_at")
    private String startedAt;

}
