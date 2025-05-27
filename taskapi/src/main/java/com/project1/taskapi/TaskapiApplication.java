package com.project1.taskapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TaskapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskapiApplication.class, args);
	}
}
