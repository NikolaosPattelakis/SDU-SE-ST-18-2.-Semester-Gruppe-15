/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the creation and maintenance of a connection to a database
 * 
 */
public class DatabaseConnector {

    private static Connection connection = null;
    
    private DatabaseConnector() {}

    public static Connection getConnection(){
        return connection;
    }
    
    /**
     * Creates and sets an sql-database connection to a specific database, based on the given parameters.
     * @param url
     * @param user
     * @param password
     * @param driver 
     */
    public static void initConnection(String url, String user, String password, String driver) {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Connection to database was not made.");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Driver was not found.");
        }
    }
    
    /**
     * Closes the established database connection.
     */
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
