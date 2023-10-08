package com.example.shortURL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShortUrlApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShortUrlApplication.class, args);
	}

}
