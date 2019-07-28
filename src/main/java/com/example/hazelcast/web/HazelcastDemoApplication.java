package com.example.hazelcast.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"config", "processors", "com.example.hazelcast.web", "services"})
public class HazelcastDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(HazelcastDemoApplication.class, args);
	}
}
