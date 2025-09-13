package com.gabriel.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
@Profile("dev")
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {

		LOG.info("Serviço de email...");
		mailSender.send(msg);
		LOG.info("email enviado");

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {

		LOG.info("Serviço de email...");
		javaMailSender.send(msg);
		LOG.info("email enviado");

	}

}
