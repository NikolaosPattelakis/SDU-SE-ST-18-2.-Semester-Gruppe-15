
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
 * Data Access Object that handles the CRUD operations of a Data Transfer Object of type "Case"
 */
public class CaseDAO implements
        CreateInterface,
        ReadInterface,
        ReadAllInterface,
        UpdateInterface,
        DeleteInterface {

    Persistence persistence;

    public CaseDAO(Persistence persistence) {
        this.persistence = persistence;
    }

    /**
     * Creates a new case at the database.
     * @param newCase 
     */
    @Override
    public void create(DTO newCase) {
        String createCasyQuery = persistence.getQuery("createCase");
        StatementController statementController = new StatementController();
        List<String> parameterList = this.getParameters(newCase);
        statementController.executeStatementWithMultipleInputs(persistence.getConnection(), createCasyQuery, parameterList);
    }

    /**
     * Deletes a case at the database.
     * @param deleteID 
     */
    @Override
    public void delete(String deleteID) {
        String deleteCaseID = persistence.getQuery("deleteCase");
        StatementController statementController = new StatementController();
        statementController.executeStatementWithSingleInput(persistence.getConnection(), deleteCaseID, deleteID);
    }

    /**
     * Reads a single specific case from the database.
     * @param caseID
     * @return 
     */
    @Override
    public DTO read(String caseID) {
        DTO caseToRead;
        String getCaseQuery = persistence.getQuery("getCase");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(caseID);
        input.add(Integer.toString(this.persistence.getCurrentUserID()));
        input.add(Integer.toString(this.persistence.getCurrentDepartment().getIDInformation().getDepartmentID()));
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(persistence.getConnection(), getCaseQuery, input);
        caseToRead = ResultSetToDTOConverter.getDTO(DTOType.CASE, resultSet);
        return caseToRead;
    }

    /**
     * Returns a list of cases from the database.
     * @return 
     */
    @Override
    public List<DTO> readAll() {
        List<DTO> caseList;
        String getAllCases = persistence.getQuery("getAllCases");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(Integer.toString(this.persistence.getCurrentUserID()));
        input.add(Integer.toString(this.persistence.getCurrentDepartment().getIDInformation().getDepartmentID()));
        ResultSet resultSet = statementController.executeStatementWithMultipleInputs(persistence.getConnection(), getAllCases, input);
        caseList = ResultSetToDTOConverter.getDTOList(DTOType.CASE, resultSet);
        return caseList;
    }

    /**
     * Updates a case at the database.
     * @param toUpdate 
     */
    @Override
    public void update(DTO toUpdate) {
        String updateCaseQuery = persistence.getQuery("updateCase");
        StatementController statementController = new StatementController();
        List<String> input = new ArrayList<>();
        input.add(Integer.toString(toUpdate.getIDInformation().getID()));
        input.add(Integer.toString(toUpdate.getIDInformation().getEmployeeID()));
        input.add(Integer.toString(toUpdate.getIDInformation().getDepartmentID()));
        input.add(toUpdate.getCaseStatus().toString());
        statementController.executeStatementWithMultipleInputs(persistence.getConnection(), updateCaseQuery, input);
    }

    /**
     * Takes the parameters of the DTO and sets them as Strings in a list. 
     * @param caseDTO
     * @return 
     */
    private List<String> getParameters(DTO caseDTO) {
        List<String> parameters = new ArrayList<>();
        parameters.add(Integer.toString(caseDTO.getBasicInformation().getCPR()));
        parameters.add(caseDTO.getLoginInformation().getUsername());
        parameters.add("Beskrivelse af hvad sagen går ud på..."); // Create: 'CaseInformation'
        parameters.add(Integer.toString(caseDTO.getIDInformation().getDepartmentID()));
        return parameters;
    }
}
