/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author S346795925
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
