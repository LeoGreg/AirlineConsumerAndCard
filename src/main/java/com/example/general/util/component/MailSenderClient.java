package com.example.general.util.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MailSenderClient {


    @Autowired
    public JavaMailSender emailSender;


    @Value("${spring.mail.username}")
    private String from;

    @Async("taskExecutorUrgent")
    @Transactional(value = Transactional.TxType.REQUIRED, dontRollbackOn = RuntimeException.class)
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(from);
        emailSender.send(message);
    }
}