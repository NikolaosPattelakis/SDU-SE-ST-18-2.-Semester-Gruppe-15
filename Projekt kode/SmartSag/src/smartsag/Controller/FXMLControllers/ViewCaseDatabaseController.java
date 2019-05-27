package smartsag.Controller.FXMLControllers;

import Model.DAO.CaseDAO;
import Smartsag.DTO.DTO;
import smartsag.DTO.enums.CaseStatus;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/*
 * FXML Controller class
 */
public class ViewCaseDatabaseController extends ViewController implements Initializable {
    
    @FXML
    private ListView listCases;
    
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private List<DTO> cases;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    /*
        Reads all cases relevant for the current employee and shows them in a ListView with their ID and case status.
    */
    @Override
    public void onViewInit() {
        CaseDAO caseDAO = new CaseDAO(getModel());
        cases = caseDAO.readAll();
        
        observableList.clear();
        for(DTO dto : cases) {
            observableList.add("Case - ID: " + dto.getIDInformation().getID() + ", Status: " + dto.getCaseStatus());
        }
        
        listCases.setItems(observableList);
    }
    
    @FXML
    private void departmentHandler(ActionEvent event) {
        
    }
    
    /*
        If the current employee has access to read case information, the information of the currently selected case should be shown.
        This feature is not fully implemented.
    */
    @FXML
    private void openHandler(ActionEvent event) {
        if(!getModel().getCurrentRole().getCasePermissions().canRead()) {
            showAlert("Du har ikke tilladelse til at læse sagsoplysninger!");
            return;
        }
        
        showAlert("Beskrivelse af hvad sagen omhandler...");
    }
    
    /*
        If the current employee has access to change case information, the status of the current case should change to open if closed, and vice versa.
    */
    @FXML
    private void statusChangeHandler(ActionEvent event) {
        if(!getModel().getCurrentRole().getCasePermissions().canEdit()) {
            showAlert("Du har ikke tilladelse til at ændre i en sag!");
            return;
        }
        
        int itemIndex = listCases.getSelectionModel().getSelectedIndex();
        if(itemIndex != -1) {
            DTO theCase = cases.get(itemIndex);
            
            String nextStatus = "CLOSED";
            if(theCase.getCaseStatus() == CaseStatus.closed) {
                nextStatus = "OPEN";
            }
            
            theCase.setCaseStatus(nextStatus);
            
            CaseDAO caseDAO = new CaseDAO(getModel());
            caseDAO.update(theCase);
            
            showAlert("Sagens status er blevet ændret, opdater venligst siden.");
        }
    }
    
    @FXML
    private void mainMenuHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getMainMedarbejderPath());
    }
    
    @FXML
    private void createEmployeeHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getCreateNewEmployeePath());
    }
    
    @FXML
    private void createCaseHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getCreateNewCasePath());
    }
    
    @FXML
    private void caseDatabaseHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getCaseDatabasePath());
    }
    
    @FXML
    private void activeHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getAktiveMedarbejderPath());
    }
    
    @FXML
    private void inactiveHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInaktiveMedarbejderPath());
    }
    
    @FXML
    private void logoutHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
}
