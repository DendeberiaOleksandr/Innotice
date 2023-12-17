package org.innotice.twitch.service.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {
    private String accessToken;
    long expiresIn;
    String tokenType;

    @JsonIgnore
    long issuedAt;
}