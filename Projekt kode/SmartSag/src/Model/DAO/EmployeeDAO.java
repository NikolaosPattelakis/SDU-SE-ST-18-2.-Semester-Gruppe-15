package Model.DAO;

import Model.Persistence;
import Model.DAO.Interfaces.UpdateInterface;
import Model.DAO.Interfaces.CreateInterface;
import Model.DAO.Interfaces.DeleteInterface;
import Model.DAO.Interfaces.LoginInterface;
import Model.DAO.Interfaces.ReadInterface;
import Smartsag.DTO.DTO;
import Model.ResultSetToDTOConverter;
import Model.StatementController;
import smartsag.DTO.enums.DTOType;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Data Access Object that handles the CRUD operations of a Data Transfer Object of type "Employee"
 * 
 */
public final class EmployeeDAO implements ReadInterface, CreateInterface, DeleteInterface, UpdateInterface, LoginInterface {

    Persistence persistence;

    public EmployeeDAO(Persistence persistence) {
        this.persistence = persistence;
    }

    /**
     * Reads a specific employee from the database.
     * @param username
     * @return 
     */
    @Override
    public DTO read(String username) {
        String getCitizenQuery = persistence.getQuery("getEmployee");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(persistence.getConnection(), getCitizenQuery, username);
        DTO employee = ResultSetToDTOConverter.getDTO(DTOType.CITIZEN, resultSet);
        return employee;
    }

    /**
     * Creates an employee at the database.
     * @param objectToSave 
     */
    @Override
    public void create(DTO objectToSave) {
        String createEmployee = persistence.getQuery("createEmployee");
        StatementController statementController = new StatementController();
        List<String> inputs = getParameters(objectToSave);
        statementController.executeStatementWithMultipleInputs(persistence.getConnection(), createEmployee, inputs);
    }

    /**
     * Deletes a specific employee based on its ID at the database.
     * @param employeeID 
     */
    @Override
    public void delete(String employeeID) {
        String deleteEmployee = persistence.getQuery("deleteEmployee");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(persistence.getConnection(), deleteEmployee, employeeID);
    }

    /**
     * Updates a specific employee at the database.
     * @param toUpdate 
     */
    @Override
    public void update(DTO toUpdate) {
        {
            String createEmployee = persistence.getQuery("updateEmployee");
            StatementController statementController = new StatementController();
            List<String> inputs = getParameters(toUpdate);
            inputs.add(Integer.toString(toUpdate.getIDInformation().getEmployeeID()));
            statementController.executeStatementWithMultipleInputs(persistence.getConnection(), createEmployee, inputs);
        }
    }

    /**
     * Takes the parameters of the DTO and sets them as Strings in a list.
     * @param employee
     * @return 
     */
    private List<String> getParameters(DTO employee) {
        List<String> parameters = new ArrayList<>();
        parameters.add(employee.getLoginInformation().getUsername());
        parameters.add(employee.getLoginInformation().getPassword());
        parameters.add(employee.getBasicInformation().getFirstName());
        parameters.add(employee.getBasicInformation().getMiddleName());
        parameters.add(employee.getBasicInformation().getLastName());
        parameters.add(Integer.toString(employee.getIDInformation().getRoleID()));
        parameters.add(employee.getBasicInformation().getName());
        return parameters;
    }

    /**
     * Sets up a specific employee as the current user via a username and a password.
     * @param username
     * @param password 
     */
    @Override
    public void login(String username, String password) {

        String loginEmployeeQuery = persistence.getQuery("loginEmployee");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(username);
        input.add(password);
        input.add(this.persistence.getCurrentDepartment().getBasicInformation().getName());
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(this.persistence.getConnection(), loginEmployeeQuery, input);

        DTO currentUser = ResultSetToDTOConverter.getDTO(DTOType.EMPLOYEE, resultSet);
        this.persistence.setCurrentUser(currentUser);

        if (currentUser.getIDInformation() != null) {
            // Login successful, now set the user ID and retrieve the employee's role
            this.persistence.setCurrentUserID(currentUser.getIDInformation().getEmployeeID());

            RoleDAO roleDAO = new RoleDAO(persistence);
            DTO role = roleDAO.read(String.valueOf(currentUser.getIDInformation().getRoleID()));

            this.persistence.setCurrentRole(role);
        }
    }
}
