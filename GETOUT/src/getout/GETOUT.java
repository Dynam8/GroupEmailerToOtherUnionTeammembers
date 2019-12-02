/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getout;
import Backend.Message;
import Backend.SendEmail;
import java.util.Scanner;
/**
 *
 * @author S331471193
 */
public class GETOUT {
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] recipients = {"stran1@ocdsb.ca", "asitu2@ocdsb.ca"};
        Message message = new Message("USERNAME", "PASS",recipients, "Tesbobt", "Tebobst");
        while (true){
            System.out.println("keep me logged in");
            String bob = sc.next();
            SendEmail em = new SendEmail(message, "USERNAME", "PASS",recipients, "Tesbobt", "Tebobst");
           
        }
        
        
        
        //System.out.println("fred smells");
    }
    
}
