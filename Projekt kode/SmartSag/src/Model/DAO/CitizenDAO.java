package Model.DAO;

import Model.DAO.Interfaces.LoginInterface;
import Model.Persistence;
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
* Data Access Object that handles the CRUD operations of a Data Transfer Object of type "Citizen"
 */
public final class CitizenDAO implements ReadInterface, LoginInterface {

    private final Persistence persistence;

    public CitizenDAO(Persistence persistence) {
        this.persistence = persistence;
    }

    /**
     * Reads a specific citizen from the database.
     * @param cpr
     * @return 
     */
    @Override
    public DTO read(String cpr) {
        String getCitizenQuery = persistence.getQuery("getCitizen");
        StatementController statementController = new StatementController();
        ResultSet resultSet = statementController.executeStatementWithSingleInput(persistence.getConnection(), getCitizenQuery, cpr);
        DTO citizen = ResultSetToDTOConverter.getDTO(DTOType.CITIZEN, resultSet);
        return citizen;
    }

    /**
     * Sets up a specific citizen as the current user via a username and a password.
     * @param username
     * @param password 
     */
    @Override
    public void login(String username, String password) {
        String loginCitizenQuery = persistence.getQuery("loginCitizen");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(username);
        input.add(password);
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(this.persistence.getConnection(), loginCitizenQuery, input);

        DTO currentUser = ResultSetToDTOConverter.getDTO(DTOType.CITIZEN, resultSet);
        this.persistence.setCurrentUser(currentUser);
        
        if(currentUser.getIDInformation() != null) {
            this.persistence.setCurrentUserID(currentUser.getIDInformation().getCitizenID());
        }
    }
}
