package com.innotice.twitch.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication(scanBasePackages = {
        "com.innotice.twitch.webhook",
        "com.innotice.security.microservice"
})
public class InnoticeTwitchWebhookApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnoticeTwitchWebhookApplication.class, args);
    }

}
