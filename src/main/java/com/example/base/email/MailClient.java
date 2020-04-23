package com.example.base.email;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailClient {

	private JavaMailSender mailSender;

	private MailContentBuilder mailContentBuilder;

	@Value("${email.from.email}")
	private String fromEmail;

	public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	public void prepareAndSend(String recipient, String templateName, Map<String, Object> maps) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			String content = mailContentBuilder.build(templateName, maps);
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(this.fromEmail);
			messageHelper.setTo(recipient);
			messageHelper.setSubject("Sample mail subject");
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
		}
	}

	public void prepareAndSend(EmailRequest emailRequest, String templateName, Map<String, Object> maps) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			String content = mailContentBuilder.build(templateName, maps);
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(emailRequest.getFrom());
			messageHelper.setTo(emailRequest.getTo());
			if (emailRequest.getBcc() != null) {
				messageHelper.setBcc(emailRequest.getBcc());
			}
			if (emailRequest.getCc() != null) {
				messageHelper.setCc(emailRequest.getCc());
			}

			messageHelper.setSubject(emailRequest.getSubject());
			messageHelper.setText(content, true);
		};
		try {
			this.mailSender.send(messagePreparator);
		} catch (MailException e) {
			System.out.println(e);
		}
	}

}