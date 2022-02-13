package com.jainsaab.ipldashboard.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jainsaab.ipldashboard.config.ApplicationProperties;
import com.jainsaab.ipldashboard.exception.IplDashboardException;
import com.jainsaab.ipldashboard.model.SendErrorEmailRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailSender {
	private final ApplicationProperties applicationProperties;
	private final RestTemplate restTemplate;
	private final URI emailServiceURI;
	private final List<String> sendTo;

	@Autowired
	public EmailSender(ApplicationProperties applicationProperties, RestTemplate restTemplate,
			@Value("${error.mail.url}") String emailServiceURL, @Value("${error.email.sendTo}") List<String> sendTo)
			throws URISyntaxException {
		this.applicationProperties = applicationProperties;
		this.restTemplate = restTemplate;
		this.emailServiceURI = new URI(emailServiceURL);
		this.sendTo = sendTo;
	}

	@Async("threadPoolTaskExecutor")
	public void sendErrorMail(IplDashboardException ex) {
		log.warn("Sending error mail for external-ref-id :: {}", ex.getExternalReferenceId());

		SendErrorEmailRequest request = new SendErrorEmailRequest();
		request.setServiceName(applicationProperties.getApplicationName());
		request.setTo(sendTo);
		request.setException(ex);
		ResponseEntity<Void> response = restTemplate.postForEntity(emailServiceURI, request, Void.class);
		
		log.warn("response :: '%s' for external-ref-id :: {}", response, ex.getExternalReferenceId());
	}
}