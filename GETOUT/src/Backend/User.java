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
    private String name;
    
    public User(String name, String email, String password,  int permission){
        this.email = email;
        this.password = password;
        this.name = name;
        this.permission = permission;
    }

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


    
}
