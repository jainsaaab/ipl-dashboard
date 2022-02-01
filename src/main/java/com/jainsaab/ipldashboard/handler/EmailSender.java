package com.jainsaab.ipldashboard.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jainsaab.ipldashboard.batch.config.ApplicationProperties;
import com.jainsaab.ipldashboard.batch.config.ErrorEmailConfig;
import com.jainsaab.ipldashboard.exception.IplDashboardException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender {
	private final ErrorEmailConfig errorEmailConfig;
	private final ApplicationProperties applicationProperties;
	
	private static final Properties PROPS;
	
	static {
		PROPS = new Properties();
		PROPS.put("mail.smtp.auth", "true");
		PROPS.put("mail.smtp.starttls.enable", "true");
		PROPS.put("mail.smtp.host", "smtp.gmail.com");
		PROPS.put("mail.smtp.port", "587"); 
	}
	
	private Session getSession() {
		return Session.getInstance(PROPS, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(errorEmailConfig.getUsername(), errorEmailConfig.getPassword());
			}
		});
	}
	
	private String getSubject(IplDashboardException ex) {
		return "Service: %s, priority: %s, errorMessage: %s"
				.formatted(applicationProperties.getApplicationName(), ex.getPriority(), ex.getErrorMessage());
	}
	
	String getContent(IplDashboardException ex) {
		String header = "Service: %s, priority: %s, errorMessage: %s, timestamp : %s"
				.formatted(applicationProperties.getApplicationName(), ex.getPriority(), ex.getErrorMessage(), ex.getTimestamp());
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stackTrace = sw.toString();
		try {
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return header.concat("\n").concat(stackTrace);
	}
	
	@Async("threadPoolTaskExecutor")
	public void sendErrorMail(IplDashboardException ex, String externalRefId) throws AddressException, MessagingException, IOException {
		log.warn("Sending error mail for external-ref-id :: {}", externalRefId);
		
		Session session = getSession();		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(errorEmailConfig.getUsername(), false));
		
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", errorEmailConfig.getSendTo())));
		msg.setSubject(getSubject(ex));
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(getContent(ex), "text/plain");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		
		Transport.send(msg);
		
		log.warn("Error mail sent for external-ref-id :: {}", externalRefId);
	}
}