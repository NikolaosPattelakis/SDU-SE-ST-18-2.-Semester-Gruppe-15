/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag;

/**
 *
 * @author Oliver
 */
public class SmartSag {

    public final static String ROLES_XML_FILEPATH = "data/roles.xml";
    public static int CURRENT_ROLE_ID;
    public static Role currentRole;
    
    public static void main(String[] args)throws Exception {
        
        DataHandler dataHandler = new DataHandler(ROLES_XML_FILEPATH);
        CURRENT_ROLE_ID = 0;
        currentRole = dataHandler.getRole(CURRENT_ROLE_ID);
        
        int lastIDonFile = Integer.parseInt(dataHandler.getLastID());
        System.out.println("Last role on roles xml file is= ");
        System.out.println(dataHandler.getRole(lastIDonFile).toString());
        
        RoleHandler roleHandler = new RoleHandler();
        roleHandler.createRole(currentRole, ROLES_XML_FILEPATH, createNewRole());

        lastIDonFile = Integer.parseInt(dataHandler.getLastID());
        System.out.println("Last role on roles xml file is= ");
        System.out.println(dataHandler.getRole(lastIDonFile).toString());      
        
    }
    
    private static Role createNewRole(){
        
        Role role = new Role();
        
        role.setName("SagsBehandler");
        
        role.setRoleCanCreate(true);
        role.setRoleCanEdit(true);
        role.setRoleCanRead(true);
        role.setRoleCanDelete(true);
        
        role.setUserCanCreate(true);
        role.setUserCanEdit(true);
        role.setUserCanRead(true);
        role.setUserCanDelete(true);
        
        role.setCaseCanCreate(true);
        role.setCaseCanEdit(true);
        role.setCaseCanRead(true);
        role.setCaseCanDelete(true);
        
        return role;
    }
}
