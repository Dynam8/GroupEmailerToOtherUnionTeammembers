/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getout;

import Backend.Login;
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
        String[] recipients = {"emailerTreeMailer@gmail.com"};
        //String username = "dfgdfgd";
        String username = "emailerTreeMailer@gmail.com";
        String password = "Wemail1Email";
        Login user = new Login(username, password);
        if (user.isValidEmail) {
            for (int i = 0; i < 1; i++) {//testing

                SendEmail em = new SendEmail(user, recipients, "Test for today" + Integer.toString(i + 1622), "this worked?");
                System.out.println("email #" + Integer.toString(i) + " successful");

            }
        }

    }

}
