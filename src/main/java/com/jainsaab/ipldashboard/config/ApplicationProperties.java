package com.jainsaab.ipldashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ApplicationProperties {
	private final String applicationName;

	public ApplicationProperties(@Value("${spring.application.name}") String applicationName) {
		this.applicationName = applicationName;
	}
}
