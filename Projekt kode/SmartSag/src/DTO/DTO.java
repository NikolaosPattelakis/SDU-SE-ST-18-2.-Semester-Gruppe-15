/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Interfaces.BuilderInterface;
import DTO.enums.CaseStatus;

/**
 *
 * @author Lupo
 */
public final class DTO{

    private IDInformation ID;
    private BasicInformation informationBasic;
    private LoginInformation loginInformation;

    private CaseStatus caseStatus;
    
    private PermissionsInformation casePermissions;
    private PermissionsInformation userPermissions;
    private PermissionsInformation rolePermissions;
    

    private DTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public IDInformation getIDInformation() {
        return this.ID;
    }
    
    public void setID(IDInformation id){
        this.ID = id;
    }

    /**
     * @return the informationPersonal
     */
    public BasicInformation getBasicInformation() {
        return informationBasic;
    }

    /**
     * @param basicInformation the informationPersonal to set
     */
    public void setUserBasicInformation(BasicInformation basicInformation) {
        this.informationBasic = basicInformation;
    }

    /**
     * @return the withLoginInformation
     */
    public LoginInformation getLoginInformation() {
        return loginInformation;
    }

    /**
     * @param loginInformation the withLoginInformation to set
     */
    public void setLoginInformation(LoginInformation loginInformation) {
        this.loginInformation = loginInformation;
    }

    /**
     * @return the caseStatus
     */
    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    /**
     * @param caseStatus the caseStatus to set
     */
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = CaseStatus.valueOf(caseStatus);
    }

    /**
     * @return the withCasePermissions
     */
    public PermissionsInformation getCasePermissions() {
        return casePermissions;
    }

    /**
     * @param casePermissions the withCasePermissions to set
     */
    public void setCasePermissions(PermissionsInformation casePermissions) {
        this.casePermissions = casePermissions;
    }

    /**
     * @return the withUserPermissions
     */
    public PermissionsInformation getUserPermissions() {
        return userPermissions;
    }

    /**
     * @param userPermissions the withUserPermissions to set
     */
    public void setUserPermissions(PermissionsInformation userPermissions) {
        this.userPermissions = userPermissions;
    }

    /**
     * @return the withRolePermissions
     */
    public PermissionsInformation getRolePermissions() {
        return rolePermissions;
    }

    /**
     * @param rolePermissions the withRolePermissions to set
     */
    public void setRolePermissions(PermissionsInformation rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public static class Builder implements BuilderInterface<DTO> {

        private DTO dto;
        
        private Builder() {
            dto = new DTO();
        }

        @Override
        public DTO build() {
            return this.dto;
        }

        public Builder withIDInformation(IDInformation id) {
            this.dto.setID(id);
            return this;
        }
        
        public Builder withBasicInformation(BasicInformation information) {
            this.dto.setUserBasicInformation(information);
            return this;
        }

        public Builder withLoginInformation(LoginInformation information) {
            this.dto.setLoginInformation(information);
            return this;
        }
        
        public Builder withCaseStatus(String status){
            this.dto.setCaseStatus(status);
            return this;
        }
        
        public Builder withCasePermissions(PermissionsInformation permissions){
            this.dto.setCasePermissions(permissions);
            return this;
        }
        
        public Builder withUserPermissions(PermissionsInformation permissions){
            this.dto.setUserPermissions(permissions);
            return this;
        }
        
        public Builder withRolePermissions(PermissionsInformation permissions){
            this.dto.setRolePermissions(permissions);
            return this;
        }
    }
}
