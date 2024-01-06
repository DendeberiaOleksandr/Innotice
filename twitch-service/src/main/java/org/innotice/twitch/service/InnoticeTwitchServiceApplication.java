package org.innotice.twitch.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableWebFluxSecurity
@SpringBootApplication(scanBasePackages = {
        "org.innotice.twitch.service",
        "org.innotice.kafka.config",
        "org.innotice.messaging",
        "org.innotice.security",
        "org.innotice.web.services"
})
    public class InnoticeTwitchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnoticeTwitchServiceApplication.class, args);
    }

}
