package com.scap.vtnreport.service;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class SentEmailService {

	public String Sentmail(ByteArrayOutputStream pdfStream,String mail) {
		
		String msg = "";

		final String auth_host = "smtp.gmail.com";
		final String auth_port = "465";
		final String auth_email = "bourneberry1@gmail.com";
		final String auth_password = "berry@1234";

		Properties props = new Properties();
		props.put("mail.smtp.host", auth_host);
		props.put("mail.smtp.socketFactory.port", auth_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", auth_port);
		props.put("mail.transport.protocol", "smtp");

		try {

			Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(auth_email, auth_password);
				}
			});

			Message message = new MimeMessage(mailSession);

			message.setFrom(new InternetAddress(auth_email)); // From

			/*** Recipient ***/
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail)); // To
			message.setSubject("Test sending mail from Java");
			message.setText("Hello mr.win, Please do not reply this mail");

			DataSource aAttachment = new ByteArrayDataSource(pdfStream.toByteArray(), "application/pdf");
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(aAttachment));
			messageBodyPart.setFileName("test.pdf");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			
		      Transport transport = mailSession.getTransport();
		      try {
		         System.out.println("Sending ....");
		         transport.connect(auth_host, 465, auth_email, auth_password);
		         transport.sendMessage(message,
		            message.getRecipients(Message.RecipientType.TO));
		         System.out.println("Sending done ...");
		      } catch (Exception e) {
		         System.err.println("Error Sending: ");
		         e.printStackTrace();

		      }
		      transport.close();
			
			
			Transport.send(message);
			
			msg = "PASS";
			System.out.println("Mail Send Successfully.=>");

		} catch (MessagingException e) {
			msg = "FAIL";
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return msg;
	}

}
