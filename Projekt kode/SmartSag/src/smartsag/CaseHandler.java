package smartsag;

import smartsag.Cases.Case;
import Persistence.DataHandler;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import smartsag.Information.TagsInformation;

/**
 * @author Sander Knudsen
 */
public class CaseHandler implements TagsInformation {

    private final DataHandler dataHandler;
    private final Role currentRole;

    public CaseHandler(Role role) {
        dataHandler = new DataHandler("data/cases.xml");
        currentRole = role;

    }

    public void createCase(int applicantID, int residenceID, int departmentID) {

        if (currentRole.isCanCreateCase() == true) {
            HashMap<String, String> caseData = this.createCaseMap(applicantID, residenceID, departmentID);
            try {
                dataHandler.createNumberedIDEntry(caseData, Tags.CASE_TAG);
            } catch (Exception ex) {
                Logger.getLogger(RoleHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Prepares and creates a HashMap for the case to be saved
     *
     * @param applicantID
     * @param residenceID
     * @param departmentID
     * @return
     */
    protected HashMap<String, String> createCaseMap(int applicantID, int residenceID, int departmentID) {
        Case newCase = new Case(applicantID, residenceID, departmentID);

        // The ':' character is used because if the element is empty, editNode() will crash upon trying to modify the element's value
        HashMap<String, String> caseData = new HashMap<>();
        caseData.put(Tags.CASE_TAG_APPLICANT, String.valueOf(applicantID));
        caseData.put(Tags.CASE_TAG_RESIDENCE, String.valueOf(residenceID));
        caseData.put(Tags.CASE_TAG_DEPARTMENT, String.valueOf(departmentID));
        caseData.put(Tags.CASE_TAG_CASE_WORKER, String.valueOf(newCase.getCaseWorkerID()));

        return caseData;
    }

    /**
     * Assigns a new case worker.
     *
     * @param caseID
     * @param newWorkerID
     */
    public void assignCaseWorker(int caseID, int newWorkerID) {
        editCaseValue(caseID, Tags.CASE_TAG_CASE_WORKER, Integer.toString(newWorkerID));
    }

    public Case getCase(int caseID) {
        String id = Integer.toString(caseID);
        int applicantID = Integer.valueOf(dataHandler.getValue(id, Tags.CASE_TAG_APPLICANT));
        int residenceID = Integer.valueOf(dataHandler.getValue(id, Tags.CASE_TAG_RESIDENCE));
        int departmentID = Integer.valueOf(dataHandler.getValue(id, Tags.CASE_TAG_DEPARTMENT));

        Case myCase = new Case(applicantID, residenceID, departmentID);
        myCase.setCaseWorkerID(Integer.parseInt(dataHandler.getValue(id, Tags.CASE_TAG_CASE_WORKER)));

        return myCase;
    }

    public void deleteCase(int caseID) {
        if (currentRole.isCanDeleteCase()) {
            dataHandler.deleteEntry(Integer.toString(caseID));
        }
    }

    private void editCaseValue(int caseID, String dataPoint, String value) {
        if (currentRole.isCanEditCase()) {
            dataHandler.editValue(Integer.toString(caseID), dataPoint, value);
        }
    }
}
