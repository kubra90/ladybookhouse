package com.ladybookhouse.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceMessage{

    @Value("${EMAIL_LB}")
    private String username;

//    @Value("${EMAIL_PW}")
//    private String password;

    private String password;


    @Autowired
    private Environment environment;






    public EmailServiceMessage(){

    }

    public void sendEmail(String provider, String toEmail, String subject, String body) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use SSL
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // TLS Port

        switch (provider.toLowerCase()) {
            case "gmail":
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                break;
            case "yahoo":
                props.put("mail.smtp.host", "smtp.mail.yahoo.com");
                props.put("mail.smtp.port", "587");
                break;
            case "outlook":
                props.put("mail.smtp.host", "smtp.office365.com");
                props.put("mail.smtp.port", "587");
                break;
            default:
                throw new IllegalArgumentException("Unsupported email provider: " + provider);
        }

        password= environment.getProperty("EMAIL_PASSWORD");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
        System.out.println("Email: " + username + ", Password: " + password);
        System.out.println("email sent successfully!");
    }
}

