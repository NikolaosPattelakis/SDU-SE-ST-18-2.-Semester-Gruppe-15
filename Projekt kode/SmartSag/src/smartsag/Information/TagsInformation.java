
package smartsag.Information;

/**
 * Holds the tags of the basic information.
 * 
 * UNFINISHED INTERFACE
 */
public interface TagsInformation {

    String TAG_ID = "ID";
    String TAG_ROLE = "role";
    String TAG_NAME = "Name";
    String TAG_INFORMATION = "Information";
    
    String CASE_TAG = "Case";
    String CASE_TAG_APPLICANT = "ApplicantID";
    String CASE_TAG_RESIDENCE = "ResidenceID";
    String CASE_TAG_DEPARTMENT = "DepartmentID";
    String CASE_TAG_CASE_WORKER = "CaseWorkerID";
    String CASE_TAG_STATUS = "Status";
    
    String CASE_TAG_INACTIVE_WORKER = "PreviousCaseWorker";
    String CASE_TAG_INACTIVE_WORKERS = "PreviousCaseWorkerList";
    
    String CASE_TAG_SUPPORTER = "ActiveSupporter";
    String CASE_TAG_SUPPORTERS = "ActiveSupporterList";
    
    String CASE_TAG_INACTIVE_SUPPORTER = "PreviousSupporter";
    String CASE_TAG_INACTIVE_SUPPORTERS = "PreviousSupporterList";

}
