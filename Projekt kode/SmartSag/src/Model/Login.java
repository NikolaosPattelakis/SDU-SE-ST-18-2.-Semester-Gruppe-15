/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lupo
 */
public class Login {
    
    private final String username;
    private String password;
    
    private final int ID;
    
    private Login instance;
    
    private Login(String username, String password, int ID){
    this.username = username;
    this.password = password;
    this.ID = ID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }
    
    public Login getInstance(){
        return this.instance;
    }
    
    public int accountIDLogin(String username, String password){
        if(this.username.equals(username)&&this.password.equals(password)){
            return this.ID;
        }
        else{
            return 0;
        }
    }
    
}
