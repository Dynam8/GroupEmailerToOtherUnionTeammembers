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
public class Login {

    public MimeMessage message;
    public String host = "smtp.gmail.com";
    public Properties props = System.getProperties();
    public String username;
    public String password;
    public Session session;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.message = returnMessage(username, password);
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
