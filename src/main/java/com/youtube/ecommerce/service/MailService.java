package com.youtube.ecommerce.service;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javamailsender;
	
	
	public void sendEmail(String email,String name) {

        try {
        	MimeMessage  message= javamailsender.createMimeMessage();
            MimeMessageHelper mimeMessage = new MimeMessageHelper(message, true);
//        	mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//        	mimeMessage.setReplyTo(name);
        	mimeMessage.setFrom("mohamedmusharaf.mm@gmail.com","the perfume store");
        	mimeMessage.setTo(new InternetAddress(email));
        	mimeMessage.setSubject(name);
        	mimeMessage.setText("welcome "+name+", the perfume store");
        	javamailsender.send(message);
        	System.out.println("message send successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}	
	
}
