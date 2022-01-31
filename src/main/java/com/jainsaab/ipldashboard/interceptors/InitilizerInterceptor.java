package com.jainsaab.ipldashboard.interceptors;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jainsaab.ipldashboard.model.RequestContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitilizerInterceptor implements HandlerInterceptor {
	private final RequestContext requestContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("Start preHandle");

		var headers = Collections.list(request.getHeaderNames()).stream()
				.collect(Collectors.toMap(e -> e, request::getHeader));

		requestContext.setExternalRefId(request.getHeader("external-ref-id"));
		
		log.debug("headers :: {}", headers);
		return true;
	}
}