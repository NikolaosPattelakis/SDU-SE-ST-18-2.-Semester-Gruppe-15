/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.Interfaces.LoginInterface;
import Model.Model;
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
public final class CitizenDAO implements ReadInterface, LoginInterface {

    private final Model model;

    public CitizenDAO(Model model) {
        this.model = model;
    }

    @Override
    public DTO read(String cpr) {
        String getCitizenQuery = model.getQuery("getCitizen");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getCitizenQuery, cpr);
        DTO citizen = ResultSetToPojoConverter.getDTO(DTOType.CITIZEN, resultSet);
        return citizen;
    }

    @Override
    public void login(String username, String password) {
        String loginCitizenQuery = model.getQuery("loginCitizen");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(username);
        input.add(password);
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(this.model.getConnection(), loginCitizenQuery, input);

        DTO currentUser = ResultSetToPojoConverter.getDTO(DTOType.CITIZEN, resultSet);
        this.model.setCurrentUser(currentUser);
        
        if(currentUser.getIDInformation() != null) {
            this.model.setCurrentUserID(currentUser.getIDInformation().getCitizenID());
        }
    }
}
