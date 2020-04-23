package com.example.base.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class EmailTestController {
	private MailClient mailClient;

	@Value("${email.from.email}")
	private String fromEmail;

	public EmailTestController(MailClient mailClient) {
		super();
		this.mailClient = mailClient;
	}

	@PostMapping("/send")
	public void emaiSend() {
		String[] to = { "user1@email.com" };
		EmailRequest emailRequest = EmailRequest.of(this.fromEmail, to, null, null, "Email Greetings");
		Map<String, Object> maps = new HashMap<>();
		maps.put("message", "alex");
		this.mailClient.prepareAndSend(emailRequest, EmailTemplateEnum.GREETING.getName(), maps);
	}
}
