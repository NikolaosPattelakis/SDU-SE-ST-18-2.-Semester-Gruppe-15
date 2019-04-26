package smartsag.Cases;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sander Knudsen
 */

public class Case {
    private final int id;
    private final int applicantID;
    private final int residenceID;
    private final int departmentID;
    private int caseWorkerID;
    private CaseStatus status;
    private ArrayList<CaseTag> tagList;
    private ArrayList<Integer> previousCaseWorkerList;
    private ArrayList<Integer> activeSupporterList;
    private ArrayList<Integer> previousSupporterList;
    
    public Case(int id, int applicantID, int residenceID, int departmentID) {
        this.id = id;
        this.applicantID = applicantID;
        this.residenceID = residenceID;
        this.departmentID = departmentID;
        
        caseWorkerID = -1;
        status = CaseStatus.Open;
        
        tagList = new ArrayList<CaseTag>();
        previousCaseWorkerList = new ArrayList<Integer>();
        activeSupporterList = new ArrayList<Integer>();
        previousSupporterList = new ArrayList<Integer>();
    }
    
    public int getID() {
        return id;
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
        if(this.caseWorkerID != -1) {
            previousCaseWorkerList.add(caseWorkerID);
        }
        this.caseWorkerID = caseWorkerID;
    }
    
    public CaseStatus getStatus() {
        return status;
    }
    
    public void setStatus(CaseStatus status) {
        this.status = status;
    }
    
    public ArrayList<CaseTag> getTagList() {
        return tagList;
    }
    
    public ArrayList<Integer> getPreviousCaseWorkerList() {
        return previousCaseWorkerList;
    }
    
    public ArrayList<Integer> getActiveSupporterList() {
        return activeSupporterList;
    }
    
    public ArrayList<Integer> getPreviousSupporterList() {
        return previousSupporterList;
    }
}
