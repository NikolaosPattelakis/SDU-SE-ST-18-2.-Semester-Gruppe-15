package smartsag;

import Persistence.DataHandler;
import Presentation.CommandInterface;


public class SmartSag {

    public final static String ROLES_XML_FILEPATH = "data/roles.xml";
    public final static String CASES_XML_FILEPATH = "data/cases.xml";

    public static int CURRENT_ROLE_ID;
    public static Role currentRole;

    public static DataHandler dataHandlerRole;
    public static DataHandler dataHandlerCase;

    public static RoleHandler roleHandler;
    public static CaseHandler caseHandler;

    /**
     * Main class <br>
     * Currently used for testing.
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        dataHandlerRole = new DataHandler(ROLES_XML_FILEPATH);
        
        //Example of command line interface with case handling
        //User is Administrator
        //User can edit and delete
        //User cannot create and read
        CommandInterface cli = new CommandInterface();
        cli.run();
        
        //Examples of methods regarding role handling
        
        setUserToKoordinator();
        
        setUserToAdministrator();
        
        roleHandler = new RoleHandler(CURRENT_ROLE_ID);
        
        createRole();
        
        deleteRole();
    }
    
    private static void setUserToKoordinator(){
        CURRENT_ROLE_ID = 1;
    }
    
    private static void setUserToAdministrator(){
        CURRENT_ROLE_ID = 0;
    }
    
    private static void createRole(){
        Role newRole = new Role();
        newRole.setName("Vikar");
        newRole.setName("Lumby");
        roleHandler.createRole(newRole);
    }
 
    private static void deleteRole(){
        int roleID = 2;
        roleHandler.deleteRole(roleID);
    }
}
