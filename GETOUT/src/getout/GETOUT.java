/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getout;
import Backend.SendEmail;
/**
 *
 * @author S331471193
 */
public class GETOUT {
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] recipients = {"stran1@ocdsb.ca", "asitu2@ocdsb.ca"};
        SendEmail em = new SendEmail("USERNAME", "PASS",recipients, "Tesbobt", "Tebobst");
        
        System.out.println("fred smells");
    }
    
}
