package com.innotice.discord.server.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableDiscoveryClient
@SpringBootApplication
public class DiscordClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordClientApplication.class, args);
	}

}
