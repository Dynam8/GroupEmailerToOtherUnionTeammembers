/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import com.google.api.services.gmail.Gmail;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

public class SendEmail{  
 
    
    public static void SendMessage(Login_DEPRECIATED user, String[] to, String subject, String body){
        try {
            user.message.setFrom(new InternetAddress(user.username));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress toAddres : toAddress) {
                user.message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            user.message.setSubject(subject);
            user.message.setText(body);
            
            try (Transport transport = user.session.getTransport("smtp")) {
                transport.connect(user.host, user.username, user.password);
                transport.sendMessage(user.message, user.message.getAllRecipients());
            }
        }
        catch (AddressException ae) {
        }
        catch (MessagingException me) {
        }
    }
    
    public static MimeMessage createEmail(String to,
            String from,
            String subject,
            String bodyText)
            throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    public static com.google.api.services.gmail.model.Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(bytes);
        com.google.api.services.gmail.model.Message message = new com.google.api.services.gmail.model.Message();
        message.setRaw(encodedEmail);
        return message;
    }

    public static com.google.api.services.gmail.model.Message sendMessage(Gmail service,
            String userId,
            MimeMessage emailContent)
            throws MessagingException, IOException {
        
        com.google.api.services.gmail.model.Message message = createMessageWithEmail(emailContent);
        message = service.users().messages().send(userId, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message;
    }

} 
  


