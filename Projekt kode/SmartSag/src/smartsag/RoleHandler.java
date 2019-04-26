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
        currentRole = new Role();
        currentRole.setInformation(dataHandler.getEntryInformation(Integer.toString(RoleID)));
    }

    /**
     * Checks whether current role can create a new role and creates one. <br>
     *
     * @param newRoleData, HashMap<String, String> <br>
     * Creates a role given an input of a HashMap<String, String>
     */
    public void createRole(HashMap<String, String> newRoleData) {
        
        if (currentRole.isCanCreateRole() == true) {
            try {
                dataHandler.createNumberedIDEntry(newRoleData, RoleHandler.TAG_ROLE);
            } catch (Exception ex) {
                Logger.getLogger(RoleHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    /**
     * Checks whether current role can edit a value of a role and edits it. <br>
     *
     * @param roleIDToEdit, Integer <br>
     * ID of the role to be edited. <br>
     * @param infoType
     * @param valueToEdit, String <br>
     * Value to be edited. <br>
     * @param newValue, String <br>
     * New value to be inserted.
     */
    public void editRoleValue(int roleIDToEdit, String infoType, String valueToEdit, String newValue) {
        
        String id = Integer.toString(roleIDToEdit);
        if (currentRole.isCanEditRole() == true) {
            dataHandler.editValue(id, infoType, valueToEdit, newValue);
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
        if (currentRole.isCanDeleteRole() == true) {
            dataHandler.deleteEntry(id);
        }
    }
}
