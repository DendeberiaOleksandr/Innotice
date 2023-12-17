package org.innotice.discord.server.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication(scanBasePackages = {
		"org.innotice.discord.server.resource.server"
})
public class DiscordServerResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordServerResourceServerApplication.class, args);
	}

}
