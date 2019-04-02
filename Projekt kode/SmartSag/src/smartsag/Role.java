/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role {
    
    private int roleID;
    private String name;
    
    private boolean roleCanCreate, roleCanEdit, roleCanRead, RoleCanDelete;
    
    private boolean userCanCreate, userCanEdit, userCanRead, userCanDelete;
    
    private boolean caseCanCreate, caseCanEdit, caseCanRead, caseCanDelete;

    public Role(){}
    
    public Role(int newRoleID, String newName, boolean canCreate, boolean canEdit,boolean canRead, boolean canDelete){
        this.roleID = newRoleID;
        this.name = newName;
        this.roleCanCreate = canCreate;
        this.roleCanEdit = canEdit;
        this.roleCanRead = canRead;
        this.RoleCanDelete = canDelete;
    }
    
    @Override
    public String toString(){
        return ("Role id= " + this.roleID + "\n" +
                "Name= " + this.name + "\n" +
                "Can create role= " + this.getRoleCanCreate() + "\n" +
                "Can edit role= " + this.getRoleCanEdit() + "\n" +
                "Can read role= " + this.getRoleCanEdit() + "\n" +
                "Can delete role= " + this.getRoleCanDelete() + "\n" + "\n"+
                "Can create user= " + this.getUserCanCreate()+ "\n" +
                "Can edit user= " + this.getUserCanEdit()+ "\n" +
                "Can read user= " + this.getUserCanRead()+ "\n" +
                "Can delete user= " + this.getUserCanDelete()+ "\n" + "\n"+
                "Can create case= " + this.getCaseCanCreate()+ "\n" +
                "Can edit case= " + this.getCaseCanEdit()+ "\n" +
                "Can read case= " + this.getCaseCanRead()+ "\n" +
                "Can delete case= " + this.getCaseCanDelete()+ "\n" + "\n"
                );
    }
    
    /**
     * @return the RoleID
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * @param ID the roleID to set
     */
    public void setID(Integer ID) {
        this.roleID = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roleCanCreate
     */
    public boolean getRoleCanCreate() {
        return roleCanCreate;
    }

    /**
     * @param roleCanCreate the roleCanCreate to set
     */
    public void setRoleCanCreate(boolean roleCanCreate) {
        this.roleCanCreate = roleCanCreate;
    }

    /**
     * @return the roleCanEdit
     */
    public boolean getRoleCanEdit() {
        return roleCanEdit;
    }

    /**
     * @param roleCanEdit the roleCanEdit to set
     */
    public void setRoleCanEdit(boolean roleCanEdit) {
        this.roleCanEdit = roleCanEdit;
    }

    /**
     * @return the roleCanRead
     */
    public boolean getRoleCanRead() {
        return roleCanRead;
    }

    /**
     * @param roleCanRead the roleCanRead to set
     */
    public void setRoleCanRead(boolean roleCanRead) {
        this.roleCanRead = roleCanRead;
    }

    /**
     * @return the RoleCanDelete
     */
    public boolean getRoleCanDelete() {
        return RoleCanDelete;
    }

    /**
     * @param RoleCanDelete the RoleCanDelete to set
     */
    public void setRoleCanDelete(boolean RoleCanDelete) {
        this.RoleCanDelete = RoleCanDelete;
    }

    /**
     * @return the userCanCreate
     */
    public boolean getUserCanCreate() {
        return userCanCreate;
    }

    /**
     * @param userCanCreate the userCanCreate to set
     */
    public void setUserCanCreate(boolean userCanCreate) {
        this.userCanCreate = userCanCreate;
    }

    /**
     * @return the userCanEdit
     */
    public boolean getUserCanEdit() {
        return userCanEdit;
    }

    /**
     * @param userCanEdit the userCanEdit to set
     */
    public void setUserCanEdit(boolean userCanEdit) {
        this.userCanEdit = userCanEdit;
    }

    /**
     * @return the userCanRead
     */
    public boolean getUserCanRead() {
        return userCanRead;
    }

    /**
     * @param userCanRead the userCanRead to set
     */
    public void setUserCanRead(boolean userCanRead) {
        this.userCanRead = userCanRead;
    }

    /**
     * @return the userCanDelete
     */
    public boolean getUserCanDelete() {
        return userCanDelete;
    }

    /**
     * @param userCanDelete the userCanDelete to set
     */
    public void setUserCanDelete(boolean userCanDelete) {
        this.userCanDelete = userCanDelete;
    }

    /**
     * @return the caseCanCreate
     */
    public boolean getCaseCanCreate() {
        return caseCanCreate;
    }

    /**
     * @param caseCanCreate the caseCanCreate to set
     */
    public void setCaseCanCreate(boolean caseCanCreate) {
        this.caseCanCreate = caseCanCreate;
    }

    /**
     * @return the caseCanEdit
     */
    public boolean getCaseCanEdit() {
        return caseCanEdit;
    }

    /**
     * @param caseCanEdit the caseCanEdit to set
     */
    public void setCaseCanEdit(boolean caseCanEdit) {
        this.caseCanEdit = caseCanEdit;
    }

    /**
     * @return the caseCanRead
     */
    public boolean getCaseCanRead() {
        return caseCanRead;
    }

    /**
     * @param caseCanRead the caseCanRead to set
     */
    public void setCaseCanRead(boolean caseCanRead) {
        this.caseCanRead = caseCanRead;
    }

    /**
     * @return the caseCanDelete
     */
    public boolean getCaseCanDelete() {
        return caseCanDelete;
    }

    /**
     * @param caseCanDelete the caseCanDelete to set
     */
    public void setCaseCanDelete(boolean caseCanDelete) {
        this.caseCanDelete = caseCanDelete;
    }

    
    
    

    
}
