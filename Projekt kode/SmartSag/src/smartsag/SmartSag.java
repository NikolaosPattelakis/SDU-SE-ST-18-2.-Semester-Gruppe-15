
package smartsag;

import Persistence.DataHandler;
import java.util.HashMap;

public class SmartSag {

    public final static String ROLES_XML_FILEPATH = "data/roles.xml";
    public final static String TEST_XML_FILEPATH = "data/test.xml";
    
    public final static String MULTIPLE_INFO_EXAMPLE_XML_FILEPATH = "data/example.xml";
    public static DataHandler loginHandler;
    
    public static int CURRENT_ROLE_ID;
    public static Role currentRole;
    
    public static DataHandler dataHandlerRole;
    public static DataHandler dataHandlerTest;
    
    
    public static RoleHandler roleHandler;
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
        
        loginHandler = new DataHandler(MULTIPLE_INFO_EXAMPLE_XML_FILEPATH);
        //System.out.println(loginHandler.getEntryInformation("Niko").toString());
        
        
                
        HashMap<String, String> info = new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        
        info.put("password", "1234");
        info.put("color", "red");
        info.put("pet", "rex");
        
        data.put("age", "25");
        data.put("workplace", "SDU");
        data.put("address", "Danmarksgade 21");
        
        HashMap<String, HashMap<String, String>> infoMap = new HashMap<>();
        infoMap.put("info", info);
        infoMap.put("data", data);
        
        loginHandler.createEntryWithMultipleMaps(infoMap, "User_account", "Laura");
        
        String mathew = loginHandler.getEntryInformation("Mathew").toString();
        System.out.println(mathew);
        
        String password = loginHandler.getValue("Mathew", "login", "color");
        System.out.println(password);
        
//        System.out.println(loginHandler.getEntryInformation("Sander").toString());
//        System.out.println(loginHandler.getEntryInformation("Sander").get("password"));
//        System.out.println(loginHandler.getEntryInformation("Sander").get("color"));
//        int i = Integer.parseInt(loginHandler.getEntryInformation("Sander").get("password"));
//        System.out.println(password == i);
        
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
        
//        createNumberedIDEntry();
    }
    
    private static void setUserToAdministrator(){
        int admID = 0;
        currentRole = new Role();
        currentRole.setInformation(dataHandlerRole.getEntryInformation(Integer.toString(admID)));
        roleHandler = new RoleHandler(admID);
    }
    
    private static void setUserToObserver(){
        int obsID = 1;
        currentRole = new Role();
        currentRole.setInformation(dataHandlerRole.getEntryInformation(Integer.toString(obsID)));
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
        roleHandler.editRoleValue(2, "Info", Tags.TAG_NAME, "Vikar");
    }
    
    private static void editRole2(){
        roleHandler.editRoleValue(2, "Info", Tags.TAG_NAME, "Sagsbehandler");
    }
    
    private static void printCurrentRoleInformation(){
        System.out.println(roleHandler.getRoleInformation(CURRENT_ROLE_ID).toString());
    }
    
    private static void printOneRoleInformation(){
        System.out.println(roleHandler.getRoleInformation(2));
    }
    
    private static void deleteLatestRole(){
        int lastID = (dataHandlerRole.getLastNumberedID());
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
        
        dataHandlerTest.createNumberedIDEntry(newEntry, TEST_XML_FILEPATH);
    }
    
}
