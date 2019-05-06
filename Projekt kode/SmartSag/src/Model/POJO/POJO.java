/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.POJO;

import Model.POJO.BasicInformation.UserBasicInformation;
import Model.POJO.Interfaces.BuilderInterface;
import Model.POJO.Interfaces.CPRInterface;
import Model.POJO.Interfaces.IdInterface;
import Model.POJO.LoginInformation.LoginInformation;
import Model.POJO.Permissions.UserPermissions;
import Model.POJO.Permissions.CasePermissions;

/**
 *
 * @author Lupo
 */
public final class POJO implements IdInterface, CPRInterface {

    private int ID;
    private int CPR;

    private UserBasicInformation basicInformation;
    private LoginInformation loginInformation;

    private CasePermissions casePermissions;
    private UserPermissions userPermissions;

    private POJO(int ID) {
        this.ID = ID;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int getCPR() {
        return this.CPR;
    }

    @Override
    public void setCPR(int cpr) {
        this.CPR = cpr;
    }

    @Override
    public int getID() {
        return this.ID;
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
    public void setBasicInformation(UserBasicInformation basicInformation) {
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

    public static class Builder implements BuilderInterface<POJO> {

        private int id;
        private int cpr;

        private UserBasicInformation userBasicInformation;
        private LoginInformation loginInformation;

        private CasePermissions casePermissions;
        private UserPermissions userPermissions;

        private Builder() {
        }

        @Override
        public POJO build() {
            return new POJO(this.id);
        }

        public Builder setID(int id) {
            this.id = id;
            return this;
        }

        public Builder setCPR(int cpr) {
            this.cpr = cpr;
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
