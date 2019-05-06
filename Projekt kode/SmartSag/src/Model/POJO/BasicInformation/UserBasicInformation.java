/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.BasicInformation;

import Model.POJO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public class UserBasicInformation implements BasicInformationInterface{

    private String firstName;
    private String middleName;
    private String lastName;
    
    private Gender gender;
    
    
    private UserBasicInformation(String firstName, String middleName, String lastName, Gender gender){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
    }
    
    public static FirstNameInterface<UserBasicInformation> builder(){
        return new Builder();
    }
    
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return this.middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
       this.middleName = middleName;
    }

    @Override
    public String getLastName() {
       return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }
    
    /**
     *
     */
    public static class Builder implements
            BuilderInterface<UserBasicInformation>,
            FirstNameInterface<UserBasicInformation>,
            MiddleNameInterface<UserBasicInformation>,
            LastNameInterface<UserBasicInformation>,
            GenderInterface<UserBasicInformation>{

        private String firstName;
        private String middleName;
        private String lastName;
        private Gender gender;
        
        private Builder(){}
        
        
        @Override
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        
        @Override
        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }
        
        @Override
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        
        @Override
        public Builder gender(String gender){
            this.gender = Gender.valueOf(gender);
            return this;
        }
        
        @Override
        public UserBasicInformation build() {
            return new UserBasicInformation(this.firstName, this.middleName, this.lastName, this.gender);
        }
    }
}
