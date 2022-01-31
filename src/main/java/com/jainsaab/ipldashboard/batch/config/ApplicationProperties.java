package com.jainsaab.ipldashboard.batch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ApplicationProperties {
	private final String applicationName;
	private final ErrorEmailConfig errorEmailConfig;

	public ApplicationProperties(@Value("${spring.application.name}") String applicationName,
			@Autowired ErrorEmailConfig errorEmailConfig) {
		this.applicationName = applicationName;
		this.errorEmailConfig = errorEmailConfig;
	}
}
