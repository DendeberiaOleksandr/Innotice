package com.innotice.model.domain.discord;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DiscordUser(
        long id,
        String username,
        String discriminator,
        @JsonProperty("global_name")
        String globalName,
        String avatar,
        Boolean bot,
        Boolean system,
        @JsonProperty("mfa_enabled")
        Boolean mfaEnabled,
        String banner,
        @JsonProperty("accent_color")
        Integer accentColor,
        String locale,
        Boolean verified,
        String email,
        Integer flags,
        @JsonProperty("premium_type")
        Integer premiumType,
        @JsonProperty("public_flags")
        Integer publicFlags,
        @JsonProperty("avatar_decoration")
        String avatarDecoration
) { }
