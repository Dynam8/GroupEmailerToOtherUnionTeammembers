//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers
/*
 * Simple user object where their program credentials are stored
 * NOT to be confused with gmail credentials, which are stored in token files
 */
package Backend;

/**
 *
 * @author Fred Chen
 */

public class User {
     private final String email;
    private final String password;//Program password, NOT gmail password! phew...
    private final int permission;
    private final String name;
    
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
