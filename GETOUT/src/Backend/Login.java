/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

/**
 *
 * @author S332896109
 */
public class Login {

    public MimeMessage message;
    public String host = "smtp.gmail.com";
    public Properties props = System.getProperties();
    public String username;
    public String password;
    public Session session;
    public boolean isValidEmail = false;

    public Login(String username, String password) {
        if (isValidEmailAddress(username)) {
            this.isValidEmail = true;
            this.username = username;
            this.password = password;
            this.message = returnMessage(username, password);
        } else {            
            System.out.println("Not a valid email address!");
        }
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private MimeMessage returnMessage(String from, String pass) {
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        this.session = Session.getDefaultInstance(props);
        return new MimeMessage(session);
    }

}
