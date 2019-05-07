/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.POJO.POJO;
import Model.Persistence.DAO;
import Model.Persistence.DatabaseConnector;
import Model.Persistence.POJOType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Lupo
 */
public class Model implements CaseModelInterface, UserModelInterface{
    
    private DAO dao;
    
    private POJO currentUser;
    private POJO user;
    
    private int currentDepartment;
    
    DatabaseConnector connector;
    Connection connection;
    PreparedStatement statement;
    ResultSet rs;
    
    public Model(){
        
    }
    
    
    public boolean authenticateUser(String username, String password){
        
        connector = new DatabaseConnector();
        connection = connector.getConnection();

        try {
            statement = connection.prepareStatement("select * from citizens where username=? and password=?");
            
            statement.setString(1, username);
            statement.setString(2, password);
            
            rs = statement.executeQuery();

            if (rs.next()) {
                this.user = dao.getPOJO(POJOType.CITIZEN, rs);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
        
    }
    
    @Override
    public void getCase() {
       
    }

    @Override
    public void createCase(POJO caseDAO) {
        if(currentUser.getCasePermissions().canCreate()== true){
            
        }
    }

    @Override
    public void editCase() {
        if(currentUser.getCasePermissions().canEdit()== true){
            
        }
    }

    @Override
    public void readFullCase() {
        if(currentUser.getCasePermissions().canReadFull()== true){
            
        }
    }

    @Override
    public void readPartialCase() {
        if(currentUser.getCasePermissions().canReadPartial()== true){
            
        }
    }

    @Override
    public void deleteCase() {
        if(currentUser.getCasePermissions().canDelete()== true){
            
        }
    }

    
    
    @Override
    public POJO getUser() {
        return this.user;
    }

    @Override
    public void createUser(POJO newUser) {
        if(currentUser.getUserPermissions().canCreate() == true){
            
        }
    }

    @Override
    public void editUser() {
        if(currentUser.getUserPermissions().canEdit()== true){
            
        }
    }

    @Override
    public void readFullUser() {
        if(currentUser.getUserPermissions().canReadFull()== true){
            
        }
    }

    @Override
    public void readPartialUser() {
        if(currentUser.getUserPermissions().canReadPartial()== true){
            
        }
    }

    @Override
    public void deleteUser() {
        if(currentUser.getUserPermissions().canDelete()== true){
            
        }
    }

    
 
    
    
    
    
}
