package com.airline.flightsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlightsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsappApplication.class, args);
	}
}
