/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lupo
 */
public class Database {
    
    Connection connection;
    String url = "jdbc:mysql://localhost:3306/smartsag";
    String username = "root";
    String password = "smartsagSDU";
    
    Statement statement;
    ResultSet resultSet;
    
    public Database(){
        
    }
    
    private void openConnection(){
        try{
        this.connection = DriverManager.getConnection(url, username, password);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void exampleQuery(String query){
        this.openConnection();
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConnection();
    }
    
    public ResultSet getResultSet(String query){
        this.exampleQuery(query);
        return this.resultSet;
    }
    
    
}
