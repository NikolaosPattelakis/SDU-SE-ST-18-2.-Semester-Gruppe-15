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
 * Data Access Object that handles the CRUD operations of a Data Transfer Object of type "Role"
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

    /**
     * Creates a new role at the database.
     * @param newRole 
     */
    @Override
    public void create(DTO newRole) {
        String createRoleQuery = model.getQuery("createRole");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(newRole);
        statementController.executeStatementWithMultipleInputs(model.getConnection(), createRoleQuery, parameterList);
    }

    /**
     * Deletes a specific role based on id at the database.
     * @param deleteID 
     */
    @Override
    public void delete(String deleteID) {
        String deleteRoleQuery = model.getQuery("deleteRole");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(model.getConnection(), deleteRoleQuery, deleteID);
    }

    /**
     * Reads a specific role based on id from the database.
     * @param roleID
     * @return 
     */
    @Override
    public DTO read(String roleID) {
        DTO role;
        String getRoleQuery = model.getQuery("getRole");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getRoleQuery, roleID);
        role = ResultSetToDTOConverter.getDTO(DTOType.ROLE, resultSet);
        return role;
    }

    /**
     * Reads all roles from the database.
     * @return 
     */
    @Override
    public List<DTO> readAll() {
        List<DTO> roleList;
        String getAllRolesQuery = model.getQuery("getAllRoles");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithNoInput(model.getConnection(), getAllRolesQuery);
        roleList = ResultSetToDTOConverter.getDTOList(DTOType.ROLE, resultSet);
        return roleList;
    }

    /**
     * Updates a specific role at the database.
     * @param toUpdate 
     */
    @Override
    public void update(DTO toUpdate) {
        String updateRoleQuery = model.getQuery("updateRole");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(toUpdate);
        parameterList.add(1, Integer.toString(toUpdate.getIDInformation().getRoleID()));
        statementController.executeStatementWithMultipleInputs(model.getConnection(), updateRoleQuery, parameterList);
    }

    /**
     * Takes the parameters of the DTO and sets them as Strings in a list.
     * @param role
     * @return 
     */
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
