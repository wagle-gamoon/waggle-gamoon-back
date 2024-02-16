package com.gamoon.gamoonbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GamoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamoonApplication.class, args);
	}

}
