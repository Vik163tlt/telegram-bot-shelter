package com.example.tgbotshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TgBotShelterApplication {
	public static void main(String[] args) {
		SpringApplication.run(TgBotShelterApplication.class, args);
	}
}