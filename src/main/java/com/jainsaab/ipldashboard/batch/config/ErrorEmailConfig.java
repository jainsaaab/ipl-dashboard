package com.jainsaab.ipldashboard.batch.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "error.email")
public class ErrorEmailConfig {
	private String username;
	private String password;
	private List<String> sendTo;
}
