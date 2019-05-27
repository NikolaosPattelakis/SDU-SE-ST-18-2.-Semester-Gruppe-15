package smartsag.Controller.FXMLControllers;

import Model.DAO.CaseDAO;
import Smartsag.DTO.BasicInformation;
import Smartsag.DTO.DTO;
import Smartsag.DTO.IDInformation;
import Smartsag.DTO.LoginInformation;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/*
    FXML Controller class
*/
public class ViewCreateNewCaseController extends ViewController implements Initializable {
    
    @FXML
    private TextField txtCitizenCPR;
    
    @FXML
    private TextField txtEmployeeUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    /*
        If the current employee has access to create new cases, creates a new case based on the entered information.
        This feature is not fully implemented as the entered case description has not been handled.
    */
    @FXML
    private void createHandler(ActionEvent event) {
        if(!getModel().getCurrentRole().getCasePermissions().canCreate()) {
            showAlert("Du har ikke tilladelse til at oprette en ny sag!");
            return;
        }
        
        String citizenCPR = txtCitizenCPR.getText();
        
        if(citizenCPR.isEmpty()) {
            showAlert("Du skal indtaste et CPR-nummer på den relevante borger.");
            return;
        }
        
        int numCPR = 0;
        try {
            numCPR = Integer.valueOf(citizenCPR);
        } catch(Exception ex) {
            showAlert("CPR-nummeret skal udelukkende bestå af tal.");
            return;
        }
        
        DTO caseDTO = DTO.builder()
                .withLoginInformation(LoginInformation.builder()
                    .username(getModel().getCurrentUser().getLoginInformation().getUsername())
                    .build())
                .withBasicInformation(BasicInformation.builder()
                        .withCPR(numCPR)
                        .build())
                .withIDInformation(IDInformation.getBuilder()
                        .withDepartmentID(getModel().getCurrentDepartmentID())
                        .build())
                .build();
        CaseDAO caseDAO = new CaseDAO(getModel());
        caseDAO.create(caseDTO);
        
        showAlert("Sagen er blevet oprettet!");
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
