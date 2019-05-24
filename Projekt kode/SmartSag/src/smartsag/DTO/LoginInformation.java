package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.LoginInformationInterface;

/**
 *
 * Class that is holding login information, to be used as a variable inside the DTO.
 */
public class LoginInformation implements LoginInformationInterface {

    private String username;
    private String password;

    private LoginInformation() {}

    /**
     * Gets builder to be used in the construction of this class object.
     *
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 
     * @return username
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     * @param username 
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     * @param password 
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Inner class used to construct the outer LoginInformation class.
     */
    public static class Builder implements BuilderInterface<LoginInformation>{

        LoginInformation loginInformation;

        private Builder(){
            this.loginInformation = new LoginInformation();
        }
        
        /**
         * Finalizes the construction of the class by building it.
         * @return 
         */
        @Override
        public LoginInformation build() {
            return this.loginInformation;
        }

        /**
         * Sets username.
         * @param username
         * @return 
         */
        public Builder username(String username) {
            this.loginInformation.setUsername(username);
            return this;
        }

        /**
         * Sets password.
         * @param password
         * @return 
         */
        public Builder password(String password) {
            this.loginInformation.setPassword(password);
            return this;
        }

    }
}
