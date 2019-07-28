package com.example.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.hazelcast.config",
		"com.example.hazelcast.processors",
		"com.example.hazelcast.web",
		"com.example.hazelcast.services"})
public class HazelcastDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(HazelcastDemoApplication.class, args);
	}
}
