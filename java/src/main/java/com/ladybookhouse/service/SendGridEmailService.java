package com.ladybookhouse.service;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Component
public class SendGridEmailService implements EmailService{



    @Autowired
    private Environment environment;


    public SendGridEmailService() {

    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        Email from = new Email("ladybookhouse@gmail.com"); // Replace with your SendGrid verified sender
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, new Email(toEmail), content);
         String sendGridApiKey = System.getenv("SENDGRID_API_KEY");
        System.out.println(sendGridApiKey);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
    }
}

