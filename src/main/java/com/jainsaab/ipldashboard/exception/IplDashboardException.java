package com.jainsaab.ipldashboard.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIncludeProperties({"errorMessage", "corelationId", "externalReferenceId", "timestamp", "priority", "stacktrace"})
public class IplDashboardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timestamp;
	private String errorMessage;
	private String corelationId;
	private String externalReferenceId;
	private String priority = "P4";
	
	@JsonProperty("stacktrace")
	public String getStackTraceStr() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		String stackTrace = sw.toString();
		try {
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stackTrace;
	}
	
	public IplDashboardException() {
	}
	
	public IplDashboardException(String message) {
		super(message);
	}

	public IplDashboardException(String message, Throwable cause) {
		super(message, cause);
	}
}