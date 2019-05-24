
package smartsag.DTO.Interfaces;

import smartsag.DTO.enums.Gender;

/**
 * 
 * Interface holding the methods for getting and setting basic information.
 */
public interface BasicInformationInterface {
    
    public String getName();
    public void setName(String name);
    
    public int getCPR();
    public void setCPR(int cpr);
    
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getMiddleName();
    public void setMiddleName(String middleName);
    public String getLastName();
    public void setLastName(String lastName);
    
    public Gender getGender();
    public void setGender(String gender);
    
    public String getEmail();
    public void setEmail(String email);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
}
