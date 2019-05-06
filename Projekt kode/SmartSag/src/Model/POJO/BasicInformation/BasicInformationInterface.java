/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO.BasicInformation;

import Model.POJO.BasicInformation.Gender;

/**
 *
 * @author Lupo
 */
public interface BasicInformationInterface {
    
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getMiddleName();
    public void setMiddleName(String middleName);
    public String getLastName();
    public void setLastName(String lastName);
    
    public Gender getGender();
    public void setGender(String gender);
}
