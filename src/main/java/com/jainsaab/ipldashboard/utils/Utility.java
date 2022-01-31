package com.jainsaab.ipldashboard.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jainsaab.ipldashboard.exception.IplDashboardException;
import com.jainsaab.ipldashboard.model.RequestContext;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utility {
	private final ObjectMapper mapper;
	private final RequestContext requestContext;
	
	public <T> String writeObjectAsString(T a) {
		try {
			return mapper.writeValueAsString(a);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public IplDashboardException prepareIplDashboardException(String message, Throwable cause) {
		IplDashboardException ex;
	
		if (null == cause) ex = new IplDashboardException(message, cause);
		else ex = new IplDashboardException(message);
		
		ex.setCorelationId(requestContext.getCrid());
		ex.setExternalReferanceId(requestContext.getExternalRefId());
		ex.setTimestamp(requestContext.getTimestamp());
		ex.setErrorMessage(message);
		
		return ex;
	}
}
