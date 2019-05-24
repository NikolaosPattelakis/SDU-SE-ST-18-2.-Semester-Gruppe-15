package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.Interfaces.IdInformationInterface;

/**
 *
 * Class that is holding id information, to be used as a variable inside the DTO.
 */
public class IDInformation implements IdInformationInterface{
    
    private int ID;
    private int citizenID;
    private int employeeID;
    private int roleID;
    private int departmentID;
    private int openingFormID;
    
    private IDInformation(){}

    /**
     * 
     * @return id
     */
    @Override
    public int getID() {
        return this.ID;
    }

    /**
     * Sets id.
     * @param id 
     */
    @Override
    public void setID(int id) {
        this.ID = id;
    }

    /**
     * 
     * @return role id
     */
    @Override
    public int getRoleID() {
        return this.roleID;
    }

    /**
     * Sets role id.
     * @param id 
     */
    @Override
    public void setRoleID(int id) {
        this.roleID = id;
    }

    /**
     * 
     * @return department id
     */
    @Override
    public int getDepartmentID() {
        return this.departmentID;
    }

    /**
     * Sets department id.
     * @param id 
     */
    @Override
    public void setDepartmentID(int id) {
        this.departmentID = id;
    }

    /**
     * 
     * @return citizen id
     */
    @Override
    public int getCitizenID() {
        return this.citizenID;
    }

    /**
     * Sets citizen id.
     * @param id 
     */
    @Override
    public void setCitizenID(int id) {
        this.citizenID = id;
    }

    /**
     * 
     * @return employee id
     */
    @Override
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Sets employee id.
     * @param id 
     */
    @Override
    public void setEmployeeID(int id) {
        this.employeeID = id;
    }

    /**
     * 
     * @return opening form id
     */
    @Override
    public int getOpeningFormID() {
        return this.openingFormID;
    }

    /**
     * Sets opening form id.
     * @param id 
     */
    @Override
    public void setOpeningFormID(int id) {
        this.openingFormID = id;
    }
    
    /**
     * Gets builder to be used in the construction of this class object.
     *
     * @return
     */
    public static Builder getBuilder(){
        return new Builder();
    }
    
    /**
     * Inner class used to construct the outer IDInformation class.
     */
    public static class Builder implements BuilderInterface<IDInformation>{

        IDInformation informationID;
        
        private Builder(){
            this.informationID = new IDInformation();
        }
        
        /**
         * Sets id.
         * @param id
         * @return 
         */
        public Builder withID(int id){
            this.informationID.setID(id);
            return this;
        }
        
        /**
         * Sets citizen id.
         * @param id
         * @return 
         */
        public Builder withCitizenID(int id){
            this.informationID.setCitizenID(id);
            return this;
        }
        
        /**
         * Sets employee id.
         * @param id
         * @return 
         */
        public Builder withEmployeeID(int id){
            this.informationID.setEmployeeID(id);
            return this;
        }
        
        /**
         * Sets department id.
         * @param id
         * @return 
         */
        public Builder withDepartmentID(int id){
            this.informationID.setDepartmentID(id);
            return this;
        }
        
        /**
         * Sets role id.
         * @param id
         * @return 
         */
        public Builder withRoleID(int id){
            this.informationID.setRoleID(id);
            return this;
        }
        
        /**
         * Sets opening form id.
         * @param id
         * @return 
         */
        public Builder withOpeningFormID(int id){
            this.informationID.setOpeningFormID(id);
            return this;
        }
        
        /**
         * Finalizes the construction of the class by building it.
         * @return 
         */
        @Override
        public IDInformation build() {
            return this.informationID;
        }

        
    }
}
