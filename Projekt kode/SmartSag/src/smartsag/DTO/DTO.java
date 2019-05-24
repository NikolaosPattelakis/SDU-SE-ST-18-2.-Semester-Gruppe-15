
package Smartsag.DTO;

import smartsag.DTO.Interfaces.BuilderInterface;
import smartsag.DTO.enums.CaseStatus;

/**
 *
 * Data Transfer Object, to be used as a persistent object that can hold all types of information that can be used in conjuction with database CRUD operations.
 */
public final class DTO{

    private IDInformation ID;
    private BasicInformation informationBasic;
    private LoginInformation loginInformation;
    private CaseInformation caseInformation;

    private CaseStatus caseStatus;
    
    private PermissionsInformation casePermissions;
    private PermissionsInformation userPermissions;
    private PermissionsInformation rolePermissions;
    

    private DTO() {}

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
     * @return id information
     */
    public IDInformation getIDInformation() {
        return this.ID;
    }
    
    /**
     * Sets id information.
     * @param id 
     */
    public void setID(IDInformation id){
        this.ID = id;
    }

    /**
     * @return basic information
     */
    public BasicInformation getBasicInformation() {
        return informationBasic;
    }

    /**
     * Sets basic information.
     * @param basicInformation 
     */
    public void setUserBasicInformation(BasicInformation basicInformation) {
        this.informationBasic = basicInformation;
    }

    /**
     * @return login information
     */
    public LoginInformation getLoginInformation() {
        return loginInformation;
    }

    /**
     * Sets login information
     * @param loginInformation 
     */
    public void setLoginInformation(LoginInformation loginInformation) {
        this.loginInformation = loginInformation;
    }

    /**
     * 
     * @return case information
     */
    public CaseInformation getCaseInformation() {
        return caseInformation;
    }
    
    /**
     * Sets case information.
     * @param caseInformation 
     */
    public void setCaseInformation(CaseInformation caseInformation) {
        this.caseInformation = caseInformation;
    }
    
    /**
     * 
     * @return case status
     */
    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    /**
     * Sets case status.
     * @param caseStatus 
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = CaseStatus.valueOf(caseStatus);
    }

    /**
     * 
     * @return case permissions
     */
    public PermissionsInformation getCasePermissions() {
        return casePermissions;
    }

    /**
     * Sets case permissions.
     * @param casePermissions 
     */
    public void setCasePermissions(PermissionsInformation casePermissions) {
        this.casePermissions = casePermissions;
    }

    /**
     * 
     * @return user permissions.
     */
    public PermissionsInformation getUserPermissions() {
        return userPermissions;
    }

   /**
    * Sets user permissions.
    * @param userPermissions 
    */
    public void setUserPermissions(PermissionsInformation userPermissions) {
        this.userPermissions = userPermissions;
    }

    /**
     * 
     * @return role permissions.
     */
    public PermissionsInformation getRolePermissions() {
        return rolePermissions;
    }

    /**
     * Sets role permissions.
     * @param rolePermissions 
     */
    public void setRolePermissions(PermissionsInformation rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    /**
     * Inner class used to construct the outer DTO class.
     */
    public static class Builder implements BuilderInterface<DTO> {

        private DTO dto;
        
        private Builder() {
            dto = new DTO();
        }

        /**
         * Finalizes the construction of the class by building it.
         * @return 
         */
        @Override
        public DTO build() {
            return this.dto;
        }

        /**
         * Sets id information.
         * @param id
         * @return 
         */
        public Builder withIDInformation(IDInformation id) {
            this.dto.setID(id);
            return this;
        }
        
        /**
         * Sets basic information.
         * @param information
         * @return 
         */
        public Builder withBasicInformation(BasicInformation information) {
            this.dto.setUserBasicInformation(information);
            return this;
        }

        /**
         * Sets login information.
         * @param information
         * @return 
         */
        public Builder withLoginInformation(LoginInformation information) {
            this.dto.setLoginInformation(information);
            return this;
        }
        
        /**
         * Sets case information.
         * @param information
         * @return 
         */
        public Builder withCaseInformation(CaseInformation information) {
            this.dto.setCaseInformation(information);
            return this;
        }
        
        /**
         * Sets case status.
         * @param status
         * @return 
         */
        public Builder withCaseStatus(String status){
            this.dto.setCaseStatus(status);
            return this;
        }
        
        /**
         * Sets case permissions.
         * @param permissions
         * @return 
         */
        public Builder withCasePermissions(PermissionsInformation permissions){
            this.dto.setCasePermissions(permissions);
            return this;
        }
        
        /**
         * Sets user permissions.
         * @param permissions
         * @return 
         */
        public Builder withUserPermissions(PermissionsInformation permissions){
            this.dto.setUserPermissions(permissions);
            return this;
        }
        
        /**
         * Sets role permissions.
         * @param permissions
         * @return 
         */
        public Builder withRolePermissions(PermissionsInformation permissions){
            this.dto.setRolePermissions(permissions);
            return this;
        }
    }
}
