package org.innotice.discord.server.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableKafka
@SpringBootApplication(
		scanBasePackages = {
				"org.innotice.discord.server.resource",
				"org.innotice.kafka.config",
				"org.innotice.messaging"
		}
)
public class DiscordServerResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordServerResourceServerApplication.class, args);
	}

}
