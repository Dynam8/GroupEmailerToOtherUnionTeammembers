/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author S332896109
 */
public class Message{
    
    public MimeMessage messageInfo;
    public String host = "smtp.gmail.com";
    public Properties props = System.getProperties();
    
    public Message(){
        
        this.messageInfo = returnMessage(username, password, recipients, subject, body, 6);
    }

    public  MimeMessage returnMessage(String from, String pass, String[] to, String subject, String body, int repeat) {
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        return new MimeMessage(session);}
  
} 
