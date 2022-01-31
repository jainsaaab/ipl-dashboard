package com.jainsaab.ipldashboard.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.jainsaab.ipldashboard.constant.Constants;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
@RequestScope
public class RequestContext {
	private final String crid = UUID.randomUUID().toString();
	private final LocalDateTime timestamp = LocalDateTime.now(Constants.INDIA_TIMEZONE);
	private String externalRefId;
}
