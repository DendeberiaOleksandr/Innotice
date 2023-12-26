package org.innotice.discord.server.resource.api;

import com.innotice.model.domain.discord.server.DiscordServerFilter;
import org.innotice.discord.server.resource.service.DiscordServerReactiveService;
import com.innotice.model.domain.discord.server.DiscordServer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class DiscordServerController {

    private final DiscordServerReactiveService service;

    public DiscordServerController(DiscordServerReactiveService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<DiscordServer> getDiscordServers(DiscordServerFilter filter) {
        return service.findAll(filter);
    }

    @GetMapping("/{id}")
    public Mono<DiscordServer> getDiscordServerById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<DiscordServer> saveDiscordServer(@RequestBody DiscordServer discordServer) {
        return service.save(discordServer);
    }

    @DeleteMapping
    public Mono<Void> deleteAllDiscordServers() {
        return service.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAllDiscordServerById(@PathVariable Long id) {
        return service.deleteById(id);
    }

}
