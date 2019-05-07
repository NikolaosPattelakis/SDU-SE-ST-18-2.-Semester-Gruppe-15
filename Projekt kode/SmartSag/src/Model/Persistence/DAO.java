/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.POJO.BasicInformation.UserBasicInformation;
import Model.POJO.LoginInformation.LoginInformation;
import Model.POJO.POJO;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lupo
 */
public class DAO {

    List<POJO> pojoList;
    

    public DAO() {

    }

    public POJO getPOJO(POJOType type, ResultSet rs) {
        POJO pojo = POJO.builder().build();
        switch (type) {
            case CITIZEN:
                pojo = this.resultSetToCitizen(rs);
                break;
            case CASE:
                break;
            case EMPLOYEE:
                pojo = this.resultSetToEmployees(rs);
                break;
            case DEPARTMENT:
                pojo = this.resultSetToDepartment(rs);
                break;
        }
        return pojo;
    }

    private POJO resultSetToCitizen(ResultSet rs) {
        try {
            POJO citizen = POJO.builder().
                    setID(rs.getInt("id")).
                    setCPR(rs.getString("cpr")).
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
            return citizen;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private POJO resultSetToEmployees(ResultSet rs) {
        try {
            POJO employee = POJO.builder().
                    setID(rs.getInt("id")).
                    loginInformation(LoginInformation.builder().
                            username(rs.getString("username")).
                            password(rs.getString("password")).
                            build()).
                    departmentID(rs.getInt("department_id")).
                    userBasicInformation(UserBasicInformation.builder().
                            firstName(rs.getString("first_name")).
                            middleName(rs.getString("middle_name")).
                            lastName(rs.getString("last_name")).
                            gender(rs.getString("gender")).
                            build()).
                    build();
            return employee;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private POJO resultSetToDepartment(ResultSet rs) {
        try {
            POJO department = POJO.builder().
                    setID(rs.getInt("id")).
                    department("name").
                    build();
            return department;
        } catch (SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
