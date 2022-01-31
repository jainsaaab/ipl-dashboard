package com.jainsaab.ipldashboard.exception;

public class IplDashboardException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IplDashboardException() {
	}
	
	public IplDashboardException(String message) {
		super(message);
	}

	public IplDashboardException(String message, Throwable cause) {
		super(message, cause);
	}
}