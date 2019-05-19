/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.DepartmentDAO;
import DTO.DTO;
import Model.DatabaseConnector;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.stream.Collectors;

/**
 *
 * @author Lupo
 */
public class Model {
    
    private final String databasePropertiesFilepath = "data/database.properties";
    private final String storedProceduresFilepath = "data/storedProcedures.properties";
    private final String configFilepath = "data/config.properties";
    
    private static Connection connection;
    
    private final HashMap<String, String> storedProcedures;
    
    private DTO currentUser;
    private int currentUserID;
    private DTO currentDepartment;
    private int currentDepartmentID;
    
    public Model() {
        this.storedProcedures = new HashMap<>();
        this.initConnection();
        this.initStoredProcedures();
        this.initCurrentDepartment();
    }
    
    private void initCurrentDepartment(){
        Properties properties = new Properties();
        String departmentID;
        try{
            FileInputStream fileInputStream = new FileInputStream(this.configFilepath);
            properties.load(fileInputStream);
        } catch(IOException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        departmentID = properties.getProperty("id");
        this.setCurrentDepartment(new DepartmentDAO(this).read(departmentID));
        this.setCurrentDepartmentID(Integer.valueOf(departmentID));
    }

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
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");

        DatabaseConnector.initConnection(url, user, password, driver);
        connection = DatabaseConnector.getConnection();
    }

    private void initStoredProcedures() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.storedProceduresFilepath);
            properties.load(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.storedProcedures.putAll(properties.entrySet().
                stream().
                collect(
                        Collectors.toMap(
                                e -> e.getKey().toString(),
                                e -> e.getValue().toString()
                        )));
    }
    
    public Connection getConnection(){
        return connection;
    }
    
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

    public int getCurrentDepartmentID() {
        return currentDepartmentID;
    }
    
    public void setCurrentDepartmentID(int departmentID) {
        this.currentDepartmentID = departmentID;
    }
}
