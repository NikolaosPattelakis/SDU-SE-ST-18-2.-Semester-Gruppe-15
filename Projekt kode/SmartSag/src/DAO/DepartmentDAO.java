/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Interfaces.CreateInterface;
import DAO.Interfaces.DeleteInterface;
import DAO.Interfaces.ReadAllInterface;
import DAO.Interfaces.ReadInterface;
import DAO.Interfaces.UpdateInterface;
import DTO.DTO;
import DTO.enums.DTOType;
import Model.Model;
import Model.ResultSetToPojoConverter;
import Model.StatementController;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lupo
 */
public class DepartmentDAO implements
        CreateInterface,
        DeleteInterface,
        ReadInterface,
        ReadAllInterface,
        UpdateInterface {

    Model model;

    public DepartmentDAO(Model model) {
        this.model = model;
    }

    @Override
    public void create(DTO newDepartment) {
        String createDepartmentQuery = model.getQuery("createDepartment");
        StatementController statementController = new StatementController();
        String departmentName = newDepartment.getBasicInformation().getName();
        statementController.executeStatementWithSingleInput(model.getConnection(), createDepartmentQuery, departmentName);
    }

    @Override
    public void delete(String deleteID) {
        String deleteDepartmentQuery = model.getQuery("deleteDepartment");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(model.getConnection(), deleteDepartmentQuery, deleteID);
    }

    @Override
    public DTO read(String departmentID) {
        String getDepartmentQuery = model.getQuery("getDepartment");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getDepartmentQuery, departmentID);
        DTO department = ResultSetToPojoConverter.getPOJO(DTOType.DEPARTMENT, resultSet);
        return department;
    }

    @Override
    public List<DTO> readAll() {
        List<DTO> departmentList;
        String getAllDepartments = model.getQuery("getAllDepartments");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithNoInput(model.getConnection(), getAllDepartments);
        departmentList = ResultSetToPojoConverter.getPOJOList(DTOType.DEPARTMENT, resultSet);
        return departmentList;
    }

    @Override
    public void update(DTO toUpdate) {
        String updateDepartmentQuery = model.getQuery("updateDepartment");
        StatementController statementController = new StatementController();
        List<String> input = this.getParameters(toUpdate);
        statementController.executeStatementWithMultipleInputs(model.getConnection(), updateDepartmentQuery, input);
    }

    private List<String> getParameters(DTO department) {
        List<String> parameters = new ArrayList<>();
        parameters.add(Integer.toString(department.getIDInformation().getDepartmentID()));
        parameters.add(department.getBasicInformation().getName());
        return parameters;
    }
}
