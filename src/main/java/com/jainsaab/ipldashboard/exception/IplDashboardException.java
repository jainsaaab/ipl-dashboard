package com.jainsaab.ipldashboard.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIncludeProperties({"errorMessage", "corelationId", "externalReferanceId", "timestamp", "priority"})
public class IplDashboardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timestamp;
	private String errorMessage;
	private String corelationId;
	private String externalReferanceId;
	private String priority = "P4";
	
	public IplDashboardException() {
	}
	
	public IplDashboardException(String message) {
		super(message);
	}

	public IplDashboardException(String message, Throwable cause) {
		super(message, cause);
	}
}