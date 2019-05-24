/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DAO.Interfaces.CreateInterface;
import Model.DAO.Interfaces.DeleteInterface;
import Model.DAO.Interfaces.ReadAllInterface;
import Model.DAO.Interfaces.ReadInterface;
import Model.DAO.Interfaces.UpdateInterface;
import Smartsag.DTO.DTO;
import smartsag.DTO.enums.DTOType;
import Model.Persistence;
import Model.ResultSetToPojoConverter;
import Model.StatementController;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lupo
 */
public class RoleDAO implements
        CreateInterface,
        ReadInterface,
        ReadAllInterface,
        UpdateInterface,
        DeleteInterface {

    Persistence model;

    RoleDAO(Persistence model) {
        this.model = model;
    }

    @Override
    public void create(DTO newRole) {
        String createRoleQuery = model.getQuery("createRole");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(newRole);
        statementController.executeStatementWithMultipleInputs(model.getConnection(), createRoleQuery, parameterList);
    }

    @Override
    public void delete(String deleteID) {
        String deleteRoleQuery = model.getQuery("deleteRole");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(model.getConnection(), deleteRoleQuery, deleteID);
    }

    @Override
    public DTO read(String roleID) {
        DTO role;
        String getRoleQuery = model.getQuery("getRole");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getRoleQuery, roleID);
        role = ResultSetToPojoConverter.getDTO(DTOType.ROLE, resultSet);
        return role;
    }

    @Override
    public List<DTO> readAll() {
        List<DTO> roleList;
        String getAllRolesQuery = model.getQuery("getAllRoles");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithNoInput(model.getConnection(), getAllRolesQuery);
        roleList = ResultSetToPojoConverter.getDTOList(DTOType.ROLE, resultSet);
        return roleList;
    }

    @Override
    public void update(DTO toUpdate) {
        String updateRoleQuery = model.getQuery("updateRole");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(toUpdate);
        parameterList.add(1, Integer.toString(toUpdate.getIDInformation().getRoleID()));
        statementController.executeStatementWithMultipleInputs(model.getConnection(), updateRoleQuery, parameterList);
    }

    private List<String> getParameters(DTO role) {
        List<String> parameters = new ArrayList<>();
        parameters.add(role.getBasicInformation().getName());
        
        parameters.add(Boolean.toString(role.getCasePermissions().canCreate()));
        parameters.add(Boolean.toString(role.getCasePermissions().canRead()));
        parameters.add(Boolean.toString(role.getCasePermissions().canEdit()));
        parameters.add(Boolean.toString(role.getCasePermissions().canDelete()));

        parameters.add(Boolean.toString(role.getUserPermissions().canCreate()));
        parameters.add(Boolean.toString(role.getUserPermissions().canRead()));
        parameters.add(Boolean.toString(role.getUserPermissions().canEdit()));
        parameters.add(Boolean.toString(role.getUserPermissions().canDelete()));

        parameters.add(Boolean.toString(role.getRolePermissions().canCreate()));
        parameters.add(Boolean.toString(role.getRolePermissions().canRead()));
        parameters.add(Boolean.toString(role.getRolePermissions().canEdit()));
        parameters.add(Boolean.toString(role.getRolePermissions().canDelete()));
        
        parameters.add(Integer.toString(role.getIDInformation().getDepartmentID()));

        return parameters;
    }
}
