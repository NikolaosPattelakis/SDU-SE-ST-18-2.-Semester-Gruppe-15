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
     * @param RoleID, int <br>
     * ID which is used by DataHandler to get current Role data.
     */
    public RoleHandler(int RoleID) {
        dataHandler = new DataHandler(RoleHandler.FILE_PATH_ROLES_XML);
        currentRole = new Role();
        currentRole.setInformation(dataHandler.getEntryInformation(RoleID));
    }
    
    public Role getCurrentRole() {
        return currentRole;
    }

    /**
     * Checks whether current role can create a new role and creates one. <br>
     *
     * @param newRoleData, HashMap<String, String> <br>
     * Creates a role given an input of a HashMap<String, String>
     */
    public void createRole(HashMap<String, String> newRoleData) {

        if (currentRole.hasInformation(RoleHandler.TAG_ROLE_CAN_CREATE)) {
            if (Boolean.parseBoolean(currentRole.getInformation().get(RoleHandler.TAG_ROLE_CAN_CREATE))) {
                try {
                    dataHandler.createEntry(newRoleData, RoleHandler.TAG_ROLE);
                } catch (Exception ex) {
                    Logger.getLogger(RoleHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
        if (currentRole.hasInformation(RoleHandler.TAG_ROLE_CAN_EDIT)
                && Boolean.parseBoolean(currentRole.getInformation().get(RoleHandler.TAG_ROLE_CAN_EDIT))
                == true) {
            dataHandler.editNode(roleIDToEdit, valueToEdit, newValue);
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
        if (currentRole.hasInformation(RoleHandler.TAG_ROLE_CAN_READ)
                && Boolean.parseBoolean(currentRole.getInformation().get(RoleHandler.TAG_ROLE_CAN_READ))
                == true) {

            roleInformation = dataHandler.getEntryInformation(roleIDToGet);

        }
        return roleInformation;
    }

    /**
     * Checks whether current role can read a roles data. <br>
     * Returns a List<HashMap<String, String>>, with all the roles.
     *
     * @return List<HashMap<String, String>>
     */
    public List<HashMap<String, String>> getAllRoleInformation() {
        List<HashMap<String, String>> allRoleInformation = new ArrayList<>();
        if (currentRole.hasInformation(RoleHandler.TAG_ROLE_CAN_READ)
                && Boolean.parseBoolean(currentRole.getInformation().get(RoleHandler.TAG_ROLE_CAN_READ))
                == true) {
            allRoleInformation = dataHandler.getListOfEntries(RoleHandler.TAG_ROLE);
        }
        return allRoleInformation;
    }

    /**
     * Checks whether current role can delete a role. <br>
     * Checks whether the role is not the administrator role (ID = 0)
     * @param roleIDToDelete, Integer <br>
     * The ID of the role to be deleted.
     */
    public void deleteRole(int roleIDToDelete) {
        if (currentRole.hasInformation(RoleHandler.TAG_ROLE_CAN_DELETE)
                && Boolean.parseBoolean(currentRole.getInformation().get(RoleHandler.TAG_ROLE_CAN_DELETE))
                && roleIDToDelete != 0
                == true) {
            dataHandler.deleteNode(roleIDToDelete);
        }
    }

}
