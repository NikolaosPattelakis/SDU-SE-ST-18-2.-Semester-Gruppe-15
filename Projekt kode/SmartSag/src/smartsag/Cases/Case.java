package smartsag.Cases;

import smartsag.Information.InformationMap;

/**
 * @author Sander Knudsen
 */
public class Case extends InformationMap {

    private final int applicantID;
    private final int residenceID;
    private final int departmentID;
    private int caseWorkerID;

     //To be worked upon
//    private ArrayList<CaseTag> tagList;
//    private ArrayList<Integer> previousCaseWorkerList;
//    private ArrayList<Integer> activeSupporterList;
//    private ArrayList<Integer> previousSupporterList;
    public Case(int applicantID, int residenceID, int departmentID) {

        this.applicantID = applicantID;
        this.residenceID = residenceID;
        this.departmentID = departmentID;

        caseWorkerID = -1;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public int getCaseWorkerID() {
        return caseWorkerID;
    }

    public void setCaseWorkerID(int caseWorkerID) {
        this.caseWorkerID = caseWorkerID;
    }
   
}
