/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Emperor Master Chen
 */
public class User {
     private String email;
    private String password;
    private int permission;
    private boolean alreadyLogin = false;
    private String name;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPermission() {
        return permission;
    }

    public boolean isAlreadyLogin() {
        return alreadyLogin;
    }
    
}
