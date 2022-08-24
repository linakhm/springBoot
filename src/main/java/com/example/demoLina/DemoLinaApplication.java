package com.example.demoLina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class DemoLinaApplication {

	public static void main(String[] args) {
		System.out.println("deb");

		SpringApplication.run(DemoLinaApplication.class, args);
		System.out.println("fin");

	}
}
