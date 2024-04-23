package com.org.smtp;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Smtp {

	public void sendMail(String mail,String subject,String body) {
		//mail sending logic
		Properties properties = System.getProperties();
		// step 1
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		
		
		// step 2
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("shubhprojectmail0000@gmail.com", "azil firr aphw cjim");
			}
		});
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("shubhprojectmail0000@gmail.com"));  
			message.setRecipient(RecipientType.TO, new InternetAddress(mail)); 
			message.setSubject(subject); 
			message.setText(body);  
			Transport.send(message);

			System.out.println("message sent");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
