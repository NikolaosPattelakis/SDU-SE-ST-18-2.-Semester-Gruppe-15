package smartsag;

import smartsag.Cases.CaseStatus;
import smartsag.Cases.Case;
import smartsag.Cases.CaseTag;
import Persistence.DataHandler;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sander Knudsen
 */

public class CaseHandler implements TagsInformation {
    private static int caseIDCounter = 0;
    private final DataHandler dataHandler;
    
    public CaseHandler() {
        dataHandler = new DataHandler("data/cases.xml");
        
        String lastID = dataHandler.getLastID();
        if(!lastID.isEmpty()) {
            caseIDCounter = Integer.parseInt(lastID);
        }
    }
    
    public Case createCase(int applicantID, int residenceID, int departmentID) {
        Case newCase = new Case(++caseIDCounter, applicantID, residenceID, departmentID);
        
        // The ':' character is used because if the element is empty, editNode() will crash upon trying to modify the element's value
        HashMap<String, String> caseData = new HashMap<>();
        caseData.put(Tags.CASE_TAG_APPLICANT, String.valueOf(applicantID));
        caseData.put(Tags.CASE_TAG_RESIDENCE, String.valueOf(residenceID));
        caseData.put(Tags.CASE_TAG_DEPARTMENT, String.valueOf(departmentID));
        caseData.put(Tags.CASE_TAG_CASE_WORKER, String.valueOf(newCase.getCaseWorkerID()));
        caseData.put(Tags.CASE_TAG_STATUS, newCase.getStatus().name());
        caseData.put(Tags.CASE_TAG_TAGS, ":");
        caseData.put(Tags.CASE_TAG_INACTIVE_WORKERS, ":");
        caseData.put(Tags.CASE_TAG_SUPPORTERS, ":");
        caseData.put(Tags.CASE_TAG_INACTIVE_SUPPORTERS, ":");
        
        try {
            dataHandler.createEntry(caseData, Tags.CASE_TAG);
        } catch (Exception ex) {
            Logger.getLogger(RoleHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return newCase;
    }
    
    public void assignCaseWorker(int caseID, int currentWorkerID, int newWorkerID) {
        editCaseValue(caseID, Tags.CASE_TAG_CASE_WORKER, String.valueOf(newWorkerID));

        if(currentWorkerID != -1) {
            String strWorkerID = String.valueOf(currentWorkerID);
            String caseWorkerList = dataHandler.getEntryInformation(caseID).get(Tags.CASE_TAG_INACTIVE_WORKERS);
            if(!caseWorkerList.contains(strWorkerID)) {
                caseWorkerList += strWorkerID + ":";
                editCaseValue(caseID, Tags.CASE_TAG_INACTIVE_WORKERS, caseWorkerList);
            }
        }
    }
    
    public void setCaseStatus(int caseID, CaseStatus status) {
        editCaseValue(caseID, Tags.CASE_TAG_STATUS, status.name());
    }
    
    public void addCaseTag(int caseID, CaseTag tag) {
        String tagList = dataHandler.getEntryInformation(caseID).get(Tags.CASE_TAG_TAGS);
        if(!tagList.contains(tag.name())) {
            tagList += tag.name() + ":";
            editCaseValue(caseID, Tags.CASE_TAG_TAGS, tagList);
        }
    }
    
    public void addCaseSupporter(int caseID, int supporterID) {
        String strSupporterID = String.valueOf(supporterID);
        String caseSupporterList = dataHandler.getEntryInformation(caseID).get(Tags.CASE_TAG_SUPPORTERS);
        if(!caseSupporterList.contains(strSupporterID)) {
            caseSupporterList += strSupporterID + ":";
            editCaseValue(caseID, Tags.CASE_TAG_SUPPORTERS, caseSupporterList);
        }
    }
    
    public void setCaseSupporterAsInactive(int caseID, int supporterID) {
        String strSupporterID = String.valueOf(supporterID);
        String caseInactiveSupporterList = dataHandler.getEntryInformation(caseID).get(Tags.CASE_TAG_INACTIVE_SUPPORTERS);
        if(!caseInactiveSupporterList.contains(strSupporterID)) {
            caseInactiveSupporterList += strSupporterID + ":";
            editCaseValue(caseID, Tags.CASE_TAG_INACTIVE_SUPPORTERS, caseInactiveSupporterList);
        }
        
        String caseSupporterList = dataHandler.getEntryInformation(caseID).get(Tags.CASE_TAG_SUPPORTERS);
        if(caseSupporterList.contains(strSupporterID)) {
            caseSupporterList = caseSupporterList.replace(strSupporterID, "");
            editCaseValue(caseID, Tags.CASE_TAG_SUPPORTERS, caseSupporterList);
        }
    }
    
    public Case getCase(int caseID) {
        HashMap<String, String> caseData = dataHandler.getEntryInformation(caseID);
        
        int applicantID = Integer.valueOf(caseData.get(Tags.CASE_TAG_APPLICANT));
        int residenceID = Integer.valueOf(caseData.get(Tags.CASE_TAG_RESIDENCE));
        int departmentID = Integer.valueOf(caseData.get(Tags.CASE_TAG_DEPARTMENT));
        
        Case myCase = new Case(caseID, applicantID, residenceID, departmentID);
        myCase.setCaseWorkerID(Integer.valueOf(caseData.get(Tags.CASE_TAG_CASE_WORKER)));
        myCase.setStatus(CaseStatus.valueOf(caseData.get(Tags.CASE_TAG_STATUS)));
        
        // Case tags
        for(String tag : caseData.get(Tags.CASE_TAG_TAGS).split(":")) {
            if(tag.isEmpty())
                continue;
            
            myCase.getTagList().add(CaseTag.valueOf(tag));
        }
        
        // Previous (inactive) case workers
        for(String previousCaseWorkerID : caseData.get(Tags.CASE_TAG_INACTIVE_WORKERS).split(":")) {
            if(previousCaseWorkerID.isEmpty())
                continue;
            
            myCase.getPreviousCaseWorkerList().add(Integer.valueOf(previousCaseWorkerID));
        }
        
        // Active supporters list
        for(String supporterID : caseData.get(Tags.CASE_TAG_SUPPORTERS).split(":")) {
            if(supporterID.isEmpty())
                continue;
            
            myCase.getActiveSupporterList().add(Integer.valueOf(supporterID));
        }
        
        // Previous (inactive) supporters
        for(String previousSupporterID : caseData.get(Tags.CASE_TAG_INACTIVE_SUPPORTERS).split(":")) {
            if(previousSupporterID.isEmpty())
                continue;
            
            myCase.getPreviousSupporterList().add(Integer.valueOf(previousSupporterID));
        }
        
        return myCase;
    }
    
    public void deleteCase(int caseID) {
        dataHandler.deleteNode(caseID);
    }
    
    private void editCaseValue(int caseID, String elementName, String value) {
        dataHandler.editNode(caseID, elementName, value);
    }
}
