package org.innotice.twitch.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication(scanBasePackages = {
        "org.innotice.twitch.service",
        "org.innotice.kafka.config",
        "org.innotice.messaging",
        "org.innotice.security.microservice",
        "org.innotice.web.services"
})
    public class InnoticeTwitchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnoticeTwitchServiceApplication.class, args);
    }

}
