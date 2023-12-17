package org.innotice.discord.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"org.innotice.security.microservice",
		"org.innotice.kafka.config",
		"org.innotice.messaging"
})
public class DiscordClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordClientApplication.class, args);
	}

}
