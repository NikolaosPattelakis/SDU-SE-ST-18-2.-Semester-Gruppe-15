/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.POJO.BasicInformation.Gender;
import Model.POJO.BasicInformation.UserBasicInformation;
import Model.POJO.POJO;
import Model.POJO.LoginInformation.LoginInformation;
import Model.Persistence.Database;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Lupo
 */
public class Model implements CaseModelInterface, UserModelInterface{
    
    private Database database;
    
    private POJO currentAccount;
    
    
    
    private void setCurrentAccount() {
        
    }
    
    public boolean checkIFIDExists(String username) throws SQLException{
        return false;
    }

    @Override
    public void getCase() {
       
    }

    @Override
    public void createCase(POJO caseDAO) {
        if(currentAccount.getCasePermissions().canCreate()== true){
            
        }
    }

    @Override
    public void editCase() {
        if(currentAccount.getCasePermissions().canEdit()== true){
            
        }
    }

    @Override
    public void readFullCase() {
        if(currentAccount.getCasePermissions().canReadFull()== true){
            
        }
    }

    @Override
    public void readPartialCase() {
        if(currentAccount.getCasePermissions().canReadPartial()== true){
            
        }
    }

    @Override
    public void deleteCase() {
        if(currentAccount.getCasePermissions().canDelete()== true){
            
        }
    }

    
    
    @Override
    public POJO getUser() {
        POJO userPOJO;
        ResultSet rs = this.database.getResultSet("select * from users where id=2");
        try{
        userPOJO = POJO.builder().
                            setID(rs.getInt("id")).
                                loginInformation(LoginInformation.builder().
                                        username(rs.getString("username")). 
                                       password(rs.getString("password")).
                                build()).
                                userBasicInformation(UserBasicInformation.builder().
                                        firstName(rs.getString("first_name")).
                                        middleName(rs.getString("middle_name")).
                                        lastName(rs.getString("last_name")).
                                        gender(rs.getString("gender")).
                                build()).
                            build();
            System.out.println(userPOJO.toString());
        return userPOJO;
        }
        catch(Exception e){
        return userPOJO = null;
        }
      
    }

    @Override
    public void createUser(POJO newUser) {
        if(currentAccount.getUserPermissions().canCreate() == true){
            
        }
    }

    @Override
    public void editUser() {
        if(currentAccount.getUserPermissions().canEdit()== true){
            
        }
    }

    @Override
    public void readFullUser() {
        if(currentAccount.getUserPermissions().canReadFull()== true){
            
        }
    }

    @Override
    public void readPartialUser() {
        if(currentAccount.getUserPermissions().canReadPartial()== true){
            
        }
    }

    @Override
    public void deleteUser() {
        if(currentAccount.getUserPermissions().canDelete()== true){
            
        }
    }

    
 
    
    
    
    
}
