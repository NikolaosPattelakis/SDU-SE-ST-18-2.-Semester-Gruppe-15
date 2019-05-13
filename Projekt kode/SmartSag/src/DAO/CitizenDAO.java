/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Model;
import DAO.Interfaces.ReadInterface;
import DTO.DTO;
import Model.ResultSetToPojoConverter;
import Model.StatementController;
import DTO.enums.DTOType;
import java.sql.ResultSet;

/**
 *
 * @author Lupo
 */
public final class CitizenDAO implements ReadInterface{

    private final Model model;

    public CitizenDAO(Model model) {
        this.model = model;
    }

    @Override
    public DTO read(String cpr) {
        String getCitizenQuery = model.getQuery("getCitizen");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(model.getConnection(), getCitizenQuery, cpr);
        DTO citizen = ResultSetToPojoConverter.getPOJO(DTOType.CITIZEN, resultSet);
        return citizen;
    }
}
