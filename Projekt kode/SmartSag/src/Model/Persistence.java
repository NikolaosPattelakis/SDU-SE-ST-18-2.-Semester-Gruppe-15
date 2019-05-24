/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.DepartmentDAO;
import Smartsag.DTO.DTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.stream.Collectors;

/**
 * Class that initiates and handles persistent data required by the program. 
 * 
 * 
 */
public class Persistence {
    
    //Filepaths for files where configuration and information needed for the initial program setup are saved.
    private final String databasePropertiesFilepath = "data/database.properties";
    private final String storedProceduresFilepath = "data/storedProcedures.properties";
    private final String configFilepath = "data/config.properties";
    private final String encryptionFilepath = "data/encryption.properties"; 
    
    private static Connection connection;
    
    //Map containing the stored procedures of the database to be used.
    private final HashMap<String, String> storedProcedures;
    
    
    private String encryptionType; 
    private String encryptionKey; 
    private String encryptionSalt; 
    
    private DTO currentUser;
    private int currentUserID;
    private DTO currentDepartment;
    private int currentDepartmentID;
    private DTO currentRole;
    
    public Persistence() {
        this.storedProcedures = new HashMap<>();
        this.initConnection();
        this.initStoredProcedures();
        this.initCurrentDepartment();
        this.initEncryptionMode();
    }
    
    /**
     * Gets department id from file and sets up the current department.
     */
    private void initCurrentDepartment(){
        Properties properties = new Properties();
        String departmentID;
        try{
            FileInputStream fileInputStream = new FileInputStream(this.configFilepath);
            properties.load(fileInputStream);
        } catch(IOException ex){
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        departmentID = properties.getProperty("departmentID");
        this.setCurrentDepartment(new DepartmentDAO(this).read(departmentID));
        this.setCurrentDepartmentID(Integer.valueOf(departmentID));
    }

    /**
     * Gets specific information required for setting up a sql-database connection and sets it up.
     */
    private void initConnection() {
        
        Properties properties = new Properties();
        String driver;
        String url;
        String user;
        String password;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.databasePropertiesFilepath);
            properties.load(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        DatabaseConnector.initConnection(url, user, password, driver);
        connection = DatabaseConnector.getConnection();
    }

    /**
     * Gets the calls of stored procedures in string form from file and saves it into a map for quick access. 
     * These stored procedures will be used to interact with the database.
     * 
     */
    private void initStoredProcedures() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.storedProceduresFilepath);
            properties.load(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.storedProcedures.putAll(properties.entrySet().
                stream().
                collect(
                        Collectors.toMap(
                                e -> e.getKey().toString(),
                                e -> e.getValue().toString()
                        )));
    }
    
    /**
     * Sets encryption variables from file.
     */
    private void initEncryptionMode(){ 
         
        Properties properties = new Properties(); 
        FileInputStream fileInputStream; 
        try { 
            fileInputStream = new FileInputStream(this.encryptionFilepath); 
            properties.load(fileInputStream); 
        } catch (IOException ex) { 
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        this.encryptionType = properties.getProperty("type"); 
        this.encryptionKey = properties.getProperty("key"); 
        this.encryptionSalt = properties.getProperty("salt"); 
    } 
    
    /**
     * Returns the persistent connection.
     * @return 
     */
    public Connection getConnection(){
        return connection;
    }
    
    /**
     * Returns a specific stored procedure persistent in map, based on input.
     * @param input
     * @return 
     */
    public String getQuery(String input){
        return this.storedProcedures.get(input);
    }

    /**
     * @return the currentUser
     */
    public DTO getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(DTO currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the currentDepartment
     */
    public DTO getCurrentDepartment() {
        return currentDepartment;
    }

    /**
     * @param currentDepartment the currentDepartment to set
     */
    public void setCurrentDepartment(DTO currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    /**
     * @return the currentUserID
     */
    public int getCurrentUserID() {
        return currentUserID;
    }

    /**
     * @param currentUserID the currentUserID to set
     */
    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    /**
     * 
     * @return the current department ID 
     */
    public int getCurrentDepartmentID() {
        return currentDepartmentID;
    }
    
    /**
     * Sets the current department ID based on input.
     * @param departmentID 
     */
    public void setCurrentDepartmentID(int departmentID) {
        this.currentDepartmentID = departmentID;
    }
    
    /**
     * 
     * @return the current Role
     */
    public DTO getCurrentRole() {
        return currentRole;
    }
    
    /**
     * Sets the current role.
     * @param role 
     */
    public void setCurrentRole(DTO role) {
        this.currentRole = role;
    }
    
    /** 
     * @return the encryptionType 
     */ 
    public String getEncryptionType() { 
        return encryptionType; 
    } 
 
    /** 
     * @return the encryptionKey 
     */ 
    public String getEncryptionKey() { 
        return encryptionKey; 
    } 
 
    /** 
     * @return the encryptionSalt 
     */ 
    public String getEncryptionSalt() { 
        return encryptionSalt; 
    } 
}
