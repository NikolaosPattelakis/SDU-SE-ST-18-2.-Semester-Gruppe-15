/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Model;
import DAO.Interfaces.UpdateInterface;
import DAO.Interfaces.CreateInterface;
import DAO.Interfaces.DeleteInterface;
import DAO.Interfaces.LoginInterface;
import DAO.Interfaces.ReadInterface;
import DTO.DTO;
import Model.ResultSetToPojoConverter;
import Model.StatementController;
import DTO.enums.DTOType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lupo
 */
public final class EmployeeDAO implements ReadInterface, CreateInterface, DeleteInterface, UpdateInterface, LoginInterface {

    Model model;

    public EmployeeDAO(Model model) {
        this.model = model;
    }

    @Override
    public DTO read(String username) {
        String getCitizenQuery = model.getQuery("getEmployee");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getCitizenQuery, username);
        DTO employee = ResultSetToPojoConverter.getDTO(DTOType.CITIZEN, resultSet);
        return employee;
    }

    @Override
    public void create(DTO objectToSave) {
        String createEmployee = model.getQuery("createEmployee");
        StatementController statementController = new StatementController();
        List<String> inputs = getParameters(objectToSave);
        statementController.executeStatementWithMultipleInputs(model.getConnection(), createEmployee, inputs);
    }

    @Override
    public void delete(String employeeID) {
        String deleteEmployee = model.getQuery("deleteEmployee");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(model.getConnection(), deleteEmployee, employeeID);
    }

    @Override
    public void update(DTO toUpdate) {
        {
            String createEmployee = model.getQuery("updateEmployee");
            StatementController statementController = new StatementController();
            List<String> inputs = getParameters(toUpdate);
            inputs.add(Integer.toString(toUpdate.getIDInformation().getEmployeeID()));
            statementController.executeStatementWithMultipleInputs(model.getConnection(), createEmployee, inputs);
        }
    }

    private List<String> getParameters(DTO employee) {
        List<String> parameters = new ArrayList<>();
        parameters.add(employee.getLoginInformation().getUsername());
        parameters.add(employee.getLoginInformation().getPassword());
        parameters.add(employee.getBasicInformation().getFirstName());
        parameters.add(employee.getBasicInformation().getMiddleName());
        parameters.add(employee.getBasicInformation().getLastName());
        return parameters;
    }

    @Override
    public void login(String username, String password) {

        String loginEmployeeQuery = model.getQuery("loginEmployee");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(username);
        input.add(password);
        input.add(this.model.getCurrentDepartment().getBasicInformation().getName());
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(this.model.getConnection(), loginEmployeeQuery, input);

        DTO currentUser = ResultSetToPojoConverter.getDTO(DTOType.EMPLOYEE, resultSet);
        this.model.setCurrentUser(currentUser);

        if(currentUser.getIDInformation() != null) {
            // Login successful, now set the user ID and retrieve the employee's role
            this.model.setCurrentUserID(currentUser.getIDInformation().getEmployeeID());
            
            RoleDAO roleDAO = new RoleDAO(model);
            DTO role = roleDAO.read(String.valueOf(currentUser.getIDInformation().getRoleID()));
            
            this.model.setCurrentRole(role);
        }
    }
}
