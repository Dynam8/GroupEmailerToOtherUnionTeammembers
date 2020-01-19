/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getout;

import Backend.Login_DEPRECIATED;
import Backend.ParseJson;
import Backend.SendEmail;
import Backend.User;
import GUI.LoginScreen;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author S331471193
 */
public class GETOUT {

    /**
     * @param args the command line arguments
     */
    public final static String ADMIN_EMAIL = "admin@admin.com";
    public static ArrayList<User> users;
    public final static String USERS_FILE_PATH = "UserCred/users.json";

    public static void main(String[] args) throws IOException {
        users = ParseJson.readFromFile("UserCred/users.json", User.class);

        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }

}
