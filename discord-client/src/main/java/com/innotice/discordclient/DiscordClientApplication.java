package com.innotice.discordclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class DiscordClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordClientApplication.class, args);
	}

}
