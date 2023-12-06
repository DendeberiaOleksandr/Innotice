package com.innotice.discord.server.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableWebFluxSecurity
@SpringBootApplication(scanBasePackages = {
		"com.innotice.discord.server.resource.server",
		"com.innotice.security.microservice"
})
public class DiscordServerResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordServerResourceServerApplication.class, args);
	}

}
