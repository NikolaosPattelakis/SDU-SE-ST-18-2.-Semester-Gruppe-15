/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

import smartsag.Information.InformationMap;

/**
 * Final Class Role, contains the important information and methods every role
 * of the system can have. extends @InformationMap <br>
 * implements @Tags
 */
public final class Role extends InformationMap implements Tags {

    private final String roleTag = Role.TAG_ROLE;
    private String name;
    private String department;

    private boolean canCreateRole = false;
    private boolean canReadRole = false;
    private boolean canEditRole = false;
    private boolean canDeleteRole = false;

    private boolean canCreateCase = false;
    private boolean canReadCase = false;
    private boolean canEditCase = false;
    private boolean canDeleteCase = false;

    private boolean canCreateUser = false;
    private boolean canReadUser = false;
    private boolean canEditUser = false;
    private boolean canDeleteUser = false;

    protected Role(){}
    
    protected Role(String newName, String newDepartment) {
        this.name = newName;
        this.department = newDepartment;
    }

    /**
     * @return the roleTag
     */
    protected String getRoleTag() {
        return roleTag;
    }

    protected String getName() {
        return this.name;
    }

    protected String getDepartment() {
        return this.department;
    }

    protected void setPermissionsRole(boolean create, boolean edit, boolean read, boolean delete) {

        this.canCreateRole = create;
        this.canEditRole = edit;
        this.canReadRole = read;
        this.canDeleteRole = delete;

    }
    
    protected void setPermissionsCase(boolean create, boolean edit, boolean read, boolean delete) {

        this.canCreateCase = create;
        this.canEditCase = edit;
        this.canReadCase = read;
        this.canDeleteCase = delete;

    }
    
    protected void setPermissionsUser(boolean create, boolean edit, boolean read, boolean delete) {

        this.canCreateUser = create;
        this.canEditUser = edit;
        this.canReadUser = read;
        this.canDeleteUser = delete;

    }

    /**
     * @return the canCreateRole
     */
    public boolean isCanCreateRole() {
        return canCreateRole;
    }

    /**
     * @return the canReadRole
     */
    public boolean isCanReadRole() {
        return canReadRole;
    }

    /**
     * @return the canEditRole
     */
    public boolean isCanEditRole() {
        return canEditRole;
    }

    /**
     * @return the canDeleteRole
     */
    public boolean isCanDeleteRole() {
        return canDeleteRole;
    }

    /**
     * @return the canCreateCase
     */
    public boolean isCanCreateCase() {
        return canCreateCase;
    }

    /**
     * @return the canReadCase
     */
    public boolean isCanReadCase() {
        return canReadCase;
    }

    /**
     * @return the canEditCase
     */
    public boolean isCanEditCase() {
        return canEditCase;
    }

    /**
     * @return the canDeleteCase
     */
    public boolean isCanDeleteCase() {
        return canDeleteCase;
    }

    /**
     * @return the canCreateUser
     */
    public boolean isCanCreateUser() {
        return canCreateUser;
    }

    /**
     * @return the canReadUser
     */
    public boolean isCanReadUser() {
        return canReadUser;
    }

    /**
     * @return the canEditUser
     */
    public boolean isCanEditUser() {
        return canEditUser;
    }

    /**
     * @return the canDeleteUser
     */
    public boolean isCanDeleteUser() {
        return canDeleteUser;
    }
}
