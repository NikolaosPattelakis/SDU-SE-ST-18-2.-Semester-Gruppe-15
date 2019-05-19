/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Controllers;

import DAO.CaseDAO;
import DTO.BasicInformation;
import DTO.DTO;
import DTO.IDInformation;
import DTO.LoginInformation;
import static View.Controllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ViewCreateNewCaseController extends ViewController implements Initializable {
    
    @FXML
    private TextField txtCitizenCPR;
    
    @FXML
    private TextField txtEmployeeUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    @FXML
    private void createHandler(ActionEvent event) {
        String citizenCPR = txtCitizenCPR.getText();
        String employeeUsername = txtEmployeeUsername.getText();
        
        if(citizenCPR.isEmpty() || employeeUsername.isEmpty()) {
            showAlert("Du skal indtaste både et CPR-nummer på borgeren og brugernavn på sagsbehandleren.");
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
                    .username(employeeUsername)
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
