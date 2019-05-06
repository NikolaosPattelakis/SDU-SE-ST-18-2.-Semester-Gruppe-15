/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.LoginInformation;

import Model.POJO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public class LoginInformation implements LoginInterface {
 
    private String username;
    private String password;
    
    private LoginInformation(String username, String password){
        this.username = username;
        this.password = password;
    }

    public static UsernameInterface<LoginInformation> builder(){
        return new Builder();
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static class Builder implements
     BuilderInterface<LoginInformation>,
             UsernameInterface<LoginInformation>,
             PasswordInterface<LoginInformation>{

        private String username;
        private String password;
        
        @Override
        public LoginInformation build() {
            return new LoginInformation(this.username, this.password);
        }

        @Override
        public PasswordInterface<LoginInformation> username(String username) {
            this.username = username;
            return this;
        }

        @Override
        public BuilderInterface<LoginInformation> password(String password) {
            this.username = password;
            return this;
        }
    
}
}
