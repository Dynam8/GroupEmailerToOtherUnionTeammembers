//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers

/*
 * Main File to run program
 */
package getout;

import Backend.ParseJson;
import Backend.User;
import GUI.LoginScreen;
import java.io.IOException;
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
        //gets a list of users from file
        users = ParseJson.readFromFile("UserCred/users.json", User.class);

        java.awt.EventQueue.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }

}
