/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO;

import Model.POJO.BasicInformation.UserBasicInformation;
import Model.POJO.Interfaces.BuilderInterface;
import Model.POJO.Interfaces.CPRInterface;
import Model.POJO.Interfaces.DepartmentInterface;
import Model.POJO.Interfaces.IdInterface;
import Model.POJO.LoginInformation.LoginInformation;
import Model.POJO.Permissions.UserPermissions;
import Model.POJO.Permissions.CasePermissions;

/**
 *
 * @author Lupo
 */
public final class POJO implements IdInterface, CPRInterface, DepartmentInterface {

    private int ID;
    private String CPR;
    
    private String department;
    private int departmentID;

    private UserBasicInformation basicInformation;
    private LoginInformation loginInformation;

    private CasePermissions casePermissions;
    private UserPermissions userPermissions;

    private POJO() {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getCPR() {
        return this.CPR;
    }

    @Override
    public void setCPR(String cpr) {
        this.CPR = cpr;
    }

    @Override
    public int getID() {
        return this.ID;
    }
    
    @Override
    public void setID(int id){
        this.ID = id;
    }

    /**
     * @return the basicInformation
     */
    public UserBasicInformation getBasicInformation() {
        return basicInformation;
    }

    /**
     * @param basicInformation the basicInformation to set
     */
    public void setUserBasicInformation(UserBasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    /**
     * @return the loginInformation
     */
    public LoginInformation getLoginInformation() {
        return loginInformation;
    }

    /**
     * @param loginInformation the loginInformation to set
     */
    public void setLoginInformation(LoginInformation loginInformation) {
        this.loginInformation = loginInformation;
    }

    /**
     * @return the casePermissions
     */
    public CasePermissions getCasePermissions() {
        return casePermissions;
    }

    /**
     * @param casePermissions the casePermissions to set
     */
    public void setCasePermissions(CasePermissions casePermissions) {
        this.casePermissions = casePermissions;
    }

    /**
     * @return the userPermissions
     */
    public UserPermissions getUserPermissions() {
        return userPermissions;
    }

    /**
     * @param userPermissions the userPermissions to set
     */
    public void setUserPermissions(UserPermissions userPermissions) {
        this.userPermissions = userPermissions;
    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int getDepartmentID() {
        return this.departmentID;
    }

    @Override
    public void setDepartmentID(int ID) {
        this.departmentID = ID;
    }

    public static class Builder implements BuilderInterface<POJO> {

        private POJO pojo;
        private int id;
        private String cpr;
        
        private String department;
        private int departmentID;

        private UserBasicInformation userBasicInformation;
        private LoginInformation loginInformation;

        private CasePermissions casePermissions;
        private UserPermissions userPermissions;

        private Builder() {
        }

        @Override
        public POJO build() {
            this.pojo = new POJO();
            this.pojo.setID(id);
            this.pojo.setCPR(cpr);
            this.pojo.setDepartment(department);
            this.pojo.setDepartmentID(departmentID);
            this.pojo.setUserBasicInformation(userBasicInformation);
            this.pojo.setLoginInformation(loginInformation);
            this.pojo.setCasePermissions(casePermissions);
            this.pojo.setUserPermissions(userPermissions);
            return this.pojo;
        }

        public Builder setID(int id) {
            this.id = id;
            return this;
        }
        
        public Builder setCPR(String cpr) {
            this.cpr = cpr;
            return this;
        }

        public Builder department(String department){
            this.department = department;
            return this;
        }
        
        public Builder departmentID(int departmentID){
            this.departmentID = departmentID;
            return this;
        }
        
        public Builder userBasicInformation(UserBasicInformation information) {
            this.userBasicInformation = information;
            return this;
        }

        public Builder loginInformation(LoginInformation information) {
            this.loginInformation = information;
            return this;
        }
        
        public Builder casePermissions(CasePermissions permissions){
            this.casePermissions = permissions;
            return this;
        }
        
        public Builder userPermissions(UserPermissions permissions){
            this.userPermissions = permissions;
            return this;
        }
    }
}
