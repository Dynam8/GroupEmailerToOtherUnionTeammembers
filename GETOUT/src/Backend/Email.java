//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers

/*
 * The class that deals with gmail information of a user.
 * This heavily relies on Gmail API, a library that allows for OAuth Authentication,
 * where one is able to log into their gmail from a browser securely, while 
 * being able to send emails through an external program.
 * As a result, the emails are sent securely and this program is safe to use.
 */

package Backend;

//**************The following imports are all to use Gmail API****************************//
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
//**********All the way to here*************************************************//


import java.util.Properties;//Socket and network connections
import java.security.GeneralSecurityException;//^

//Email related******
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//***************


//File IO**********
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
//*******************


//General list manipulation
import java.util.Collections;
import java.util.List;
//**************************



public class Email {

    private final String APPLICATION_NAME = "GETOUT";
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();//Json library that reads and writes
    
    /*A word about tokens:
     *They are how gmail API securely logs some one into their gmail.
     *The token does not contain any passwords, rather it contains a 'key' that
     *it sends to the Gmail servers to verify that the program's request to access an account's
     *info (such as sending on their behalf)
     *is valid and was given permission by the Google servers after a user logged in.
    */
    private final String TOKENS_DIRECTORY_PATH = "UserCred/tokens";//Where each token is stored

    
    private final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_COMPOSE);
    /*Scopes determine how much info a program is requesting from the gmail servers
     *Please feel at ease as our program is only requesting permission to send emails,
     *Therefore we cannot 'hack' your account, or access your password.
    */
    private final String CREDENTIALS_FILE_PATH = "credentials.json";
        /*The credentials store our gmail api identification; We have registered
     *our program with google cloud services.
    */
    
    private Gmail service;//The gmail API object that sends requests to google's servers to create emails 

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
   //The following method gets the user to log in to their account, and retrieves the token. 
   private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String name) throws IOException {
        
        // Load client secrets from file; the program key/authorization file
        InputStream in = Email.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        
        //*****Using the credentials file, we use it to build our request class to be sent to Google's servers*****
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH));
        DataStore<StoredCredential> dataStore = dataStoreFactory.getDataStore(name);
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setCredentialDataStore(dataStore)
                .setAccessType("offline")
                .build();
        //************************************************************************************
        
        //Establishes the port/socket to communicate with Google's servers
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
       //******Finally opens the new window, and retrieves the user's token after they log in
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public Email(String name) throws GeneralSecurityException, IOException {
        
        //Google api's transport object
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
       
        //Builds the user email object; 
        //The service object is used to send emails and contains all the necessary user information
        this.service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, name.substring(0, name.indexOf('@'))))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    //MimeMessages are part of the java mail api
    //They establish a port to connect with, and
    //all the necessary email information, such as to, from, etc.
    private MimeMessage createEmail(String[] to,
            String from,
            String subject,
            String bodyText)
            throws MessagingException {
        
        Properties props = new Properties();//Local computer's configurations, time, etc.
        Session session = Session.getDefaultInstance(props, null);//Interval of time
        //where all information is stored until the email is sent; cookies

        MimeMessage email = new MimeMessage(session);//Creates email object

        email.setFrom(new InternetAddress(from));
        for (String i : to) {
            email.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress(i)); //Adding recipients
        }
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    //Message class takes a mimemessage class and encrypts it to make it more secure
    private Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    //Method that is called when one wants to send an email
    public void sendEmail(String to[],
            String from,
            String subject,
            String bodyText) throws IOException, MessagingException {
        Message message = createMessageWithEmail(createEmail(to, from, subject, bodyText));//Creates message with necessary information
        message = service  //Message is then passed to gmail's api service object where it then sends it through gmail servers
                .users()
                .messages()
                .send("me", message)
                .execute();

        
    }

    public Gmail getService() {
        return service;
    }
}
