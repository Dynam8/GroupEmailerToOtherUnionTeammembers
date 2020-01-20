//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers
/*
 * Simple user object to store user in attendance sheets.
 * Is called to send emails to missing people and keep track of who is present
 */
package Database;

/**
 * @author Ashwin Boni Bangari
 */
public class UserAttendance {
    private String name;
    private boolean present;
    private String email;
    
    public UserAttendance(String name, boolean present, String email){
        this.name = name;
        this.present = present;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public boolean getPresent() {
        return present;
    }
    
    public void setPresent(boolean present) {
        this.present = present;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
