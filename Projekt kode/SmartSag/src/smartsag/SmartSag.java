
package smartsag;

import Persistence.DataHandler;
import Presentation.CommandInterface;
import java.util.HashMap;
import smartsag.Cases.Case;
import smartsag.Cases.CaseStatus;
import smartsag.Cases.CaseTag;

public class SmartSag {

    public final static String ROLES_XML_FILEPATH = "data/roles.xml";
    public final static String TEST_XML_FILEPATH = "data/test.xml";
    public static int CURRENT_ROLE_ID;
    public static Role currentRole;
    
    public static DataHandler dataHandlerRole;
    public static DataHandler dataHandlerTest;
    
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
    public static void main(String[] args)throws Exception {
        
        dataHandlerRole = new DataHandler(ROLES_XML_FILEPATH);
        dataHandlerTest = new DataHandler(TEST_XML_FILEPATH);
        
        caseHandler = new CaseHandler(); 
         
        setUserToAdministrator();
        
        CommandInterface commandInterface = new CommandInterface(); 
        commandInterface.run(); 
        //Uncomment and pick one user:
        
//        setUserToAdministrator();
//        setUserToObserver();
        
        //Prints information of current role
        
//        printCurrentRoleInformation();
        
        //Uncomment and comment the methods below to test how they work.
        
//        createRole();
//        editRole1();
//        editRole2();
//        printOneRoleInformation();
//        printAllRoles();
//        deleteRole(2);
//        deleteLatestRole();
        
        
        //Creates an entry on test.xml
        //Showcases how all entries can be added
        
//        createEntry();
    }
    
    private static void setUserToAdministrator(){
        int admID = 0;
        currentRole = new Role();
        currentRole.setInformation(dataHandlerRole.getEntryInformation(admID));
        roleHandler = new RoleHandler(admID);
    }
    
    private static void setUserToObserver(){
        int obsID = 1;
        currentRole = new Role();
        currentRole.setInformation(dataHandlerRole.getEntryInformation(obsID));
        roleHandler = new RoleHandler(obsID);
    }
    
    private static void createRole(){
        HashMap<String, String> newRole = new HashMap<>();
        newRole.put(Tags.TAG_NAME, "Psykolog");
        newRole.put(Tags.TAG_ROLE_CAN_CREATE, "0");
        newRole.put(Tags.TAG_ROLE_CAN_EDIT, "1");
        newRole.put(Tags.TAG_ROLE_CAN_READ, "1");
        newRole.put(Tags.TAG_ROLE_CAN_DELETE, "0");
        
        roleHandler.createRole(newRole);
    }
    
    private static void editRole1(){
        roleHandler.editRoleValue(2, Tags.TAG_NAME, "Vikar");
    }
    
    private static void editRole2(){
        roleHandler.editRoleValue(2, Tags.TAG_NAME, "Sagsbehandler");
    }
    
    private static void printCurrentRoleInformation(){
        System.out.println(roleHandler.getRoleInformation(CURRENT_ROLE_ID).toString());
    }
    
    private static void printOneRoleInformation(){
        System.out.println(roleHandler.getRoleInformation(2));
    }
    
    private static void printAllRoles(){
        System.out.println(roleHandler.getAllRoleInformation().toString());
    }
    
    private static void deleteLatestRole(){
        int lastID = Integer.parseInt(dataHandlerRole.getLastID());
        roleHandler.deleteRole(lastID);
    }
    
    private static void deleteRole(int roleID){
        roleHandler.deleteRole(roleID);
    }
    private static void createEntry(){
        HashMap<String,String> newEntry = new HashMap<>();
        newEntry.put(Tags.TAG_NAME, "Mike");
        newEntry.put("Age", "40");
        newEntry.put("Occupation", "Chemical Engineer");
        
        dataHandlerTest.createEntry(newEntry, TEST_XML_FILEPATH);
    }
    
    private static void caseTests() {
        Case myCase = caseHandler.getCase(1);//caseHandler.createCase(1, 5, 22);
        caseHandler.setCaseStatus(myCase.getID(), CaseStatus.Open);
        //caseHandler.addCaseTag(myCase.getID(), CaseTag.TestTag);
        //caseHandler.addCaseSupporter(myCase.getID(), 54);
        //caseHandler.setCaseSupporterAsInactive(myCase.getID(), 54);
        //caseHandler.assignCaseWorker(myCase.getID(), myCase.getCaseWorkerID(), 84);
        //caseHandler.deleteCase(myCase.getID());
    }
    
}
