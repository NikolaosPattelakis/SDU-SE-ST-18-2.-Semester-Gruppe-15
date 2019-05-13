/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.enums.Gender;
import DTO.Interfaces.BasicInformationInterface;
import DTO.Interfaces.BuilderInterface;

/**
 *
 * @author Lupo
 */
public class BasicInformation implements BasicInformationInterface {

    private int cpr;
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;

    private Gender gender;

    private BasicInformation() {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int getCPR(){
        return this.cpr;
    }
    
    @Override
    public void setCPR(int cpr){
        this.cpr = cpr;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    

    public static class Builder implements BuilderInterface<BasicInformation> {

        BasicInformation informationPersonal;

        private Builder() {
            this.informationPersonal = new BasicInformation();
        }

        public Builder withName(String name) {
            this.informationPersonal.setName(name);
            return this;
        }
        
        public Builder withCPR(int cpr){
            this.informationPersonal.setCPR(cpr);
            return this;
        }
        
        public Builder withFirstName(String firstName) {
            this.informationPersonal.setFirstName(firstName);
            return this;
        }

        public Builder withMiddleName(String middleName) {
            this.informationPersonal.setMiddleName(middleName);
            return this;
        }
        
        public Builder withLastName(String lastName) {
            this.informationPersonal.setLastName(lastName);
            return this;
        }

        public Builder withGender(String gender) {
            this.informationPersonal.setGender(gender);
            return this;
        }

        public BasicInformation build() {
            return this.informationPersonal;
        }
    }
}
