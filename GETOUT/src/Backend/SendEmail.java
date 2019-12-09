/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

public class SendEmail{  
 
    
    public static void SendMessage(Login user, String[] to, String subject, String body){
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

} 
  


