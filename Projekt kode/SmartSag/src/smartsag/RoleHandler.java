package smartsag;

import Persistence.DataHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles roles based on permissions. <br>
 * Permissions are based on the current role upon initialization.
 *
 */
public final class RoleHandler implements Tags, FilePathRoles {

    private final Role currentRole;
    private final DataHandler dataHandler;

    /**
     * Upon constructor calling. <br>
     * Creates an instance of DataHandler with the file path for the XML files
     * of roles. <br>
     * Creates an instance of the current Role based on ID. <br>
     *
     * @param RoleID, Integer <br>
     * ID which is used by DataHandler to get current Role data.
     */
    public RoleHandler(int RoleID) {
        dataHandler = new DataHandler(RoleHandler.FILE_PATH_ROLES_XML);
        currentRole = this.getRole(RoleID);

    }

    public Role getCurrentRole() {
        return currentRole;
    }

    /**
     * Checks whether current role can create a new role and creates one. <br>
     *
     * @param newRole
     */
    public void createRole(Role newRole) {

        if (currentRole.isCanCreateRole() == true) {
            HashMap<String, String> roleData = this.createRoleMap(newRole);
            try {
                dataHandler.createNumberedIDEntry(roleData, Tags.TAG_ROLE);
            } catch (Exception ex) {
                Logger.getLogger(RoleHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected HashMap<String, String> createRoleMap(Role newRole){
        HashMap<String, String> roleMap = new HashMap<>();
        
        roleMap.put(Tags.TAG_NAME, newRole.getName());
        roleMap.put(Tags.CASE_TAG_DEPARTMENT, newRole.getDepartment());
        
        roleMap.put(Tags.TAG_ROLE_CAN_CREATE, Boolean.toString(newRole.isCanCreateRole()));
        roleMap.put(Tags.TAG_ROLE_CAN_EDIT, Boolean.toString(newRole.isCanEditRole()));
        roleMap.put(Tags.TAG_ROLE_CAN_READ, Boolean.toString(newRole.isCanReadRole()));
        roleMap.put(Tags.TAG_ROLE_CAN_DELETE, Boolean.toString(newRole.isCanDeleteRole()));
        
        roleMap.put(Tags.TAG_CASE_CAN_CREATE, Boolean.toString(newRole.isCanCreateCase()));
        roleMap.put(Tags.TAG_CASE_CAN_EDIT, Boolean.toString(newRole.isCanEditCase()));
        roleMap.put(Tags.TAG_CASE_CAN_READ, Boolean.toString(newRole.isCanReadCase()));
        roleMap.put(Tags.TAG_CASE_CAN_DELETE, Boolean.toString(newRole.isCanDeleteCase()));
        
        roleMap.put(Tags.TAG_USER_CAN_CREATE, Boolean.toString(newRole.isCanCreateUser()));
        roleMap.put(Tags.TAG_USER_CAN_EDIT, Boolean.toString(newRole.isCanEditUser()));
        roleMap.put(Tags.TAG_USER_CAN_READ, Boolean.toString(newRole.isCanReadUser()));
        roleMap.put(Tags.TAG_USER_CAN_DELETE, Boolean.toString(newRole.isCanDeleteUser()));
        
        return roleMap;
    }

    /**
     * Returns role from database, based on id.
     *
     * @param id
     * @return
     */
    public Role getRole(int id) {

        String ID = Integer.toString(id);
        Role role = new Role();

        role.setName(dataHandler.getValue(ID, Tags.TAG_NAME));
        role.setDepartment(dataHandler.getValue(ID, Tags.CASE_TAG_DEPARTMENT));
        role.setId(id);
        role.setPermissionsCase(this.getCaseCanCreate(ID), this.getCaseCanEdit(ID), this.getCaseCanRead(ID), this.getCaseCanDelete(ID));
        role.setPermissionsRole(this.getRoleCanCreate(ID), this.getRoleCanEdit(ID), this.getRoleCanRead(ID), this.getRoleCanDelete(ID));
        role.setPermissionsUser(this.getUserCanCreate(ID), this.getUserCanEdit(ID), this.getUserCanRead(ID), this.getUserCanDelete(ID));

        return role;
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getCaseCanCreate(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_CASE_CAN_CREATE));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getCaseCanRead(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_CASE_CAN_READ));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getCaseCanEdit(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_CASE_CAN_EDIT));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getCaseCanDelete(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_CASE_CAN_DELETE));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getUserCanCreate(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_USER_CAN_CREATE));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getUserCanRead(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_CASE_CAN_READ));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getUserCanEdit(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_USER_CAN_EDIT));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getUserCanDelete(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_USER_CAN_DELETE));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getRoleCanCreate(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_ROLE_CAN_CREATE));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getRoleCanRead(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_ROLE_CAN_READ));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getRoleCanEdit(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_ROLE_CAN_EDIT));
    }

    /**
     * Gets permission based on id
     *
     * @param id
     * @return
     */
    private boolean getRoleCanDelete(String id) {
        return Boolean.parseBoolean(dataHandler.getValue(id, Tags.TAG_ROLE_CAN_DELETE));
    }

    /**
     * Checks whether current role can edit a value of a role and edits it. <br>
     *
     * @param roleIDToEdit, Integer <br>
     * ID of the role to be edited. <br>
     * @param valueToEdit, String <br>
     * Value to be edited. <br>
     * @param newValue, String <br>
     * New value to be inserted.
     */
    public void editRoleValue(int roleIDToEdit, String valueToEdit, String newValue) {

        String id = Integer.toString(roleIDToEdit);
        if (currentRole.isCanEditRole() == true) {
            dataHandler.editValue(id, valueToEdit, newValue);
        }
    }

    /**
     * Checks whether current role can read a roles data. <br>
     * Returns a HashMap<String, String>, with a roles information based on an
     * ID.
     *
     * @param roleIDToGet, Integer <br>
     * The ID of the role to be read.
     * @return HashMap<String, String> <br>
     * R
     */
    public HashMap<String, String> getRoleInformation(int roleIDToGet) {
        HashMap<String, String> roleInformation = new HashMap<>();
        if (currentRole.isCanReadRole() == true) {

            roleInformation = dataHandler.getEntryInformation(Integer.toString(roleIDToGet));

        }
        return roleInformation;
    }

    /**
     * Checks whether current role can delete a role. <br>
     * Checks whether the role is not the administrator role (ID = 0)
     *
     * @param roleIDToDelete, Integer <br>
     * The ID of the role to be deleted.
     */
    public void deleteRole(int roleIDToDelete) {
        String id = Integer.toString(roleIDToDelete);
        if (currentRole.isCanDeleteRole() && dataHandler.getLastNumberedID() != 0) {
            dataHandler.deleteEntry(id);
        }
    }
}
