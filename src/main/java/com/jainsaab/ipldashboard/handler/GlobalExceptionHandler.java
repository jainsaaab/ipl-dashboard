package com.jainsaab.ipldashboard.handler;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jainsaab.ipldashboard.exception.IplDashboardException;
import com.jainsaab.ipldashboard.model.RequestContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final EmailSender emailSender;
	private final RequestContext requestContext;
	
	@ExceptionHandler(value = IplDashboardException.class)
	public ResponseEntity<IplDashboardException> handleException(IplDashboardException ex) {
		log.error(ex.getErrorMessage(), ex);
		
		try {
			emailSender.sendErrorMail(ex, requestContext.getExternalRefId());
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<IplDashboardException>(ex, HttpStatus.BAD_REQUEST);
	}
}
