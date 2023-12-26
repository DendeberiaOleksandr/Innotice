package org.innotice.twitch.service.api.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innotice.model.domain.twitch.StreamerData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/channels/search")
public class SearchChannelsApiController {

    private final WebClient twitchWebClient;
    private final ObjectMapper OBJECT_MAPPER;

    public SearchChannelsApiController(@Qualifier("twitchWebClient") WebClient twitchWebClient) {
        this.twitchWebClient = twitchWebClient;
        this.OBJECT_MAPPER = new ObjectMapper();
    }

    @GetMapping
    public Mono<ResponseEntity<StreamerData>> searchStreamer(
            @RequestParam("query") String query
    ) {
        return twitchWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.twitch.tv")
                        .path("/helix/search/channels")
                        .queryParam("query", query)
                        .build()
                )
                .retrieve()
                .bodyToMono(ChannelsSearchResponse.class)
                .map(response -> response.getData().stream().filter(sd-> query.equalsIgnoreCase(sd.getBroadcasterLogin())).findFirst().orElse(null))
                .map(ResponseEntity::ok);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ChannelsSearchResponse {
        private List<StreamerData> data;
    }

}
