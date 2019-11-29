/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail{
    
    private String username;
    private String password;
    private String subject;
    private String body;
    private String[] recipients;
    
    public SendEmail(String username, String password, String[] recipients, String subject, String body){
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.body = body;             
        this.recipients = recipients;
        sendFromGMail(username, password, recipients, subject, body, 6);
    }

    


    private  void sendFromGMail(String from, String pass, String[] to, String subject, String body, int repeat) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setText(body);
            
            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host, from, pass);
                transport.sendMessage(message, message.getAllRecipients());
            }
        }
        catch (AddressException ae) {
        }
        catch (MessagingException me) {
        }
    }
}
