package com.micro.app.func;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleMail(String sendTo, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(subject);
		message.setText(content);
		mailSender.send(message);
	}

	public void sendHtmlMail(String sendTo, String subject, String content) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(subject);

			helper.setText(content, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

}
