package com.jainsaab.ipldashboard.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jainsaab.ipldashboard.exception.IplDashboardException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final EmailSender emailSender;
	
	@ExceptionHandler(value = IplDashboardException.class)
	public ResponseEntity<IplDashboardException> handleException(IplDashboardException ex) {
		log.error(ex.getErrorMessage(), ex);
		
		emailSender.sendErrorMail(ex);
		
		return new ResponseEntity<IplDashboardException>(ex, HttpStatus.BAD_REQUEST);
	}
}
