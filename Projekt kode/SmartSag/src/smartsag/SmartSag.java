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
    
    public static void main(String[] args) {
        
        DataHandler dataHandler = new DataHandler(ROLES_XML_FILEPATH);
        
        
        Role role = new Role();
        role = createAdministrator();
        
        try{
        dataHandler.createRolesXML(role);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
    private static Role createAdministrator(){
        Role role = new Role();
        
        role.setID(0);
        role.setName("Administrator");
        
        role.setRoleCanCreate(true);
        role.setRoleCanEdit(false);
        role.setRoleCanRead(true);
        role.setRoleCanDelete(true);
        
        role.setUserCanCreate(true);
        role.setUserCanEdit(false);
        role.setUserCanRead(false);
        role.setUserCanDelete(true);
        
        role.setCaseCanCreate(false);
        role.setCaseCanEdit(false);
        role.setCaseCanRead(false);
        role.setCaseCanDelete(true);
        
        return role;
    }
}
