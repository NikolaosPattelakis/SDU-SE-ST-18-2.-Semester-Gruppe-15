/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Interfaces.BuilderInterface;
import DTO.Interfaces.IdInformationInterface;

/**
 *
 * @author Lupo
 */
public class IDInformation implements IdInformationInterface{
    
    private int ID;
    private int citizenID;
    private int employeeID;
    private int roleID;
    private int departmentID;
    private int openingFormID;
    
    private IDInformation(){}

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void setID(int id) {
        this.ID = id;
    }

    @Override
    public int getRoleID() {
        return this.roleID;
    }

    @Override
    public void setRoleID(int id) {
        this.roleID = id;
    }

    @Override
    public int getDepartmentID() {
        return this.departmentID;
    }

    @Override
    public void setDepartmentID(int id) {
        this.departmentID = id;
    }

    @Override
    public int getCitizenID() {
        return this.citizenID;
    }

    @Override
    public void setCitizenID(int id) {
        this.citizenID = id;
    }

    @Override
    public int getEmployeeID() {
        return this.employeeID;
    }

    @Override
    public void setEmployeeID(int id) {
        this.employeeID = id;
    }

    @Override
    public int getOpeningFormID() {
        return this.openingFormID;
    }

    @Override
    public void setOpeningFormID(int id) {
        this.openingFormID = id;
    }
    
    public static Builder getBuilder(){
        return new Builder();
    }
    
    public static class Builder implements BuilderInterface<IDInformation>{

        IDInformation informationID;
        
        private Builder(){
            this.informationID = new IDInformation();
        }
        
        public Builder withID(int id){
            this.informationID.setID(id);
            return this;
        }
        
        public Builder withCitizenID(int id){
            this.informationID.setCitizenID(id);
            return this;
        }
        
        public Builder withEmployeeID(int id){
            this.informationID.setEmployeeID(id);
            return this;
        }
        
        public Builder withDepartmentID(int id){
            this.informationID.setDepartmentID(id);
            return this;
        }
        
        public Builder withRoleID(int id){
            this.informationID.setRoleID(id);
            return this;
        }
        
        public Builder withOpeningFormID(int id){
            this.informationID.setOpeningFormID(id);
            return this;
        }
        @Override
        public IDInformation build() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
    }
}
