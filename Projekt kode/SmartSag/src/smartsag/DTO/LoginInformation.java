/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.LoginInformationInterface;

/**
 *
 * @author Lupo
 */
public class LoginInformation implements LoginInformationInterface {

    private String username;
    private String password;

    private LoginInformation() {}

    public static Builder builder() {
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

    public static class Builder implements BuilderInterface<LoginInformation>{

        LoginInformation loginInformation;

        private Builder(){
            this.loginInformation = new LoginInformation();
        }
        
        @Override
        public LoginInformation build() {
            return this.loginInformation;
        }

        public Builder username(String username) {
            this.loginInformation.setUsername(username);
            return this;
        }

        public Builder password(String password) {
            this.loginInformation.setPassword(password);
            return this;
        }

    }
}
