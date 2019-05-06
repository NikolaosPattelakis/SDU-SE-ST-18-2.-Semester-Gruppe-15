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
    String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/unudfdlq";
    String username = "unudfdlq";
    String password = "UwPFfoHd-ZqUG6vSB-RALHC8NFDqmtQW";
    
    Statement statement;
    ResultSet resultSet;
    
    public Database(){
        
    }
    
    private void openConnection(){
        // Initialize driver
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
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
