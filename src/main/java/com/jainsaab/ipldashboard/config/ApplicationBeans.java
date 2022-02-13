package com.jainsaab.ipldashboard.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationBeans {
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return Executors.newFixedThreadPool(5);
	}
	
	@Bean
	public RestTemplate defaultRestTemplate() {
		return new RestTemplate();
	}
}
