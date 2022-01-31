package com.jainsaab.ipldashboard.model;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

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
	private String externalRefId;
}
