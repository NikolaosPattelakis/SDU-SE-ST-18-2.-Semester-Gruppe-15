package Model.DAO;

import Model.DAO.Interfaces.CreateInterface;
import Model.DAO.Interfaces.DeleteInterface;
import Model.DAO.Interfaces.ReadAllInterface;
import Model.DAO.Interfaces.ReadInterface;
import Model.DAO.Interfaces.UpdateInterface;
import Smartsag.DTO.DTO;
import smartsag.DTO.enums.DTOType;
import Model.Persistence;
import Model.ResultSetToDTOConverter;
import Model.StatementController;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
* Data Access Object that handles the CRUD operations of a Data Transfer Object of type "Department"
 */
public class DepartmentDAO implements
        CreateInterface,
        DeleteInterface,
        ReadInterface,
        ReadAllInterface,
        UpdateInterface {

    Persistence persistence;

    public DepartmentDAO(Persistence persistence) {
        this.persistence = persistence;
    }

    /**
     * Creates a new department in the database.
     * @param newDepartment 
     */
    @Override
    public void create(DTO newDepartment) {
        String createDepartmentQuery = persistence.getQuery("createDepartment");
        StatementController statementController = new StatementController();
        String departmentName = newDepartment.getBasicInformation().getName();
        statementController.executeStatementWithSingleInput(persistence.getConnection(), createDepartmentQuery, departmentName);
    }

    /**
     * Deletes a department from the database.
     * @param deleteID 
     */
    @Override
    public void delete(String deleteID) {
        String deleteDepartmentQuery = persistence.getQuery("deleteDepartment");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(persistence.getConnection(), deleteDepartmentQuery, deleteID);
    }

    /**
     * Reads a specific department based on its ID from the database.
     * @param departmentID
     * @return 
     */
    @Override
    public DTO read(String departmentID) {
        String getDepartmentQuery = persistence.getQuery("getDepartment");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(persistence.getConnection(), getDepartmentQuery, departmentID);
        DTO department = ResultSetToDTOConverter.getDTO(DTOType.DEPARTMENT, resultSet);
        return department;
    }

    /**
     * Reads all the departments from the database.
     * @return 
     */
    @Override
    public List<DTO> readAll() {
        List<DTO> departmentList;
        String getAllDepartments = persistence.getQuery("getAllDepartments");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithNoInput(persistence.getConnection(), getAllDepartments);
        departmentList = ResultSetToDTOConverter.getDTOList(DTOType.DEPARTMENT, resultSet);
        return departmentList;
    }

    /**
     * Updates a specific department from the database.
     * @param toUpdate 
     */
    @Override
    public void update(DTO toUpdate) {
        String updateDepartmentQuery = persistence.getQuery("updateDepartment");
        StatementController statementController = new StatementController();
        List<String> input = this.getParameters(toUpdate);
        statementController.executeStatementWithMultipleInputs(persistence.getConnection(), updateDepartmentQuery, input);
    }

    /**
     * Takes the parameters of the DTO and sets them as Strings in a list.
     * @param department
     * @return 
     */
    private List<String> getParameters(DTO department) {
        List<String> parameters = new ArrayList<>();
        parameters.add(Integer.toString(department.getIDInformation().getDepartmentID()));
        parameters.add(department.getBasicInformation().getName());
        return parameters;
    }
}
