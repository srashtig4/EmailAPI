package com.email.emailapi.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String message) {

        boolean f= false;

        String from = "justforalex17@gmail.com";
        //variable for host
        String host = "smtp.gmail.com";

        //get system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES"+ properties);

        //set properties

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.ssl.trust", host);


        //1-> get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("srashti@contaque.com", "");
            }
        });

        session.setDebug(true);
        //2-> get message
        MimeMessage text = new MimeMessage(session);

        try {
                text.setFrom(from);
                text.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                text.setSubject(subject);
                text.setText(message);

        //3-> send message using transport class
            Transport.send(text);
            System.out.println("sent email successfully--------------------");
            f = true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
