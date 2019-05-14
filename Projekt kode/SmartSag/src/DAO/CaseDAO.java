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
public class CaseDAO implements
        CreateInterface,
        ReadInterface,
        ReadAllInterface,
        UpdateInterface,
        DeleteInterface {

    Model model;

    public CaseDAO(Model model) {
        this.model = model;
    }

    @Override
    public void create(DTO newCase) {
        String createCasyQuery = model.getQuery("createCase");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(newCase);
        statementController.executeStatementWithMultipleInputs(model.getConnection(), createCasyQuery, parameterList);
    }

    @Override
    public void delete(String deleteID) {
        String deleteCaseID = model.getQuery("deleteCase");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(model.getConnection(), deleteCaseID, deleteID);
    }

    @Override
    public DTO read(String caseID) {
        DTO caseToRead;
        String getCaseQuery = model.getQuery("getCase");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(caseID);
        input.add(Integer.toString(this.model.getCurrentUserID()));
        input.add(Integer.toString(this.model.getCurrentDepartment().getIDInformation().getDepartmentID()));
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(model.getConnection(), getCaseQuery, input);
        caseToRead = ResultSetToPojoConverter.getDTO(DTOType.CASE, resultSet);
        return caseToRead;
    }

    @Override
    public List<DTO> readAll() {
        List<DTO> caseList;
        String getAllCases = model.getQuery("getAllCases");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(Integer.toString(this.model.getCurrentUserID()));
        input.add(Integer.toString(this.model.getCurrentDepartment().getIDInformation().getDepartmentID()));
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(model.getConnection(), getAllCases, input);
        caseList = ResultSetToPojoConverter.getDTOList(DTOType.CASE, resultSet);
        return caseList;
    }

    @Override
    public void update(DTO toUpdate) {
        String updateCaseQuery = model.getQuery("updateCase");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(Integer.toString(toUpdate.getIDInformation().getID()));
        input.add(Integer.toString(toUpdate.getIDInformation().getEmployeeID()));
        input.add(Integer.toString(toUpdate.getIDInformation().getDepartmentID()));
        input.add(toUpdate.getCaseStatus().toString());
        statementController.executeStatementWithMultipleInputs(model.getConnection(), updateCaseQuery, input);
    }

    private List<String> getParameters(DTO caseDTO) {
        List<String> parameters = new ArrayList<>();
        parameters.add(Integer.toString(caseDTO.getIDInformation().getCitizenID()));
        parameters.add(Integer.toString(caseDTO.getIDInformation().getEmployeeID()));
        parameters.add(Integer.toString(caseDTO.getIDInformation().getDepartmentID()));
        parameters.add(Integer.toString(caseDTO.getIDInformation().getOpeningFormID()));
        parameters.add(caseDTO.getCaseStatus().toString());
        return parameters;
    }
}
