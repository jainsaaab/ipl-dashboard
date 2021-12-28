package com.jainsaab.ipldashboard.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utility {
	private final ObjectMapper mapper;
	
	public <T> String writeObjectAsString(T a) {
		try {
			return mapper.writeValueAsString(a);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
