/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag.Controller.FXMLControllers;

import Model.DAO.EmployeeDAO;
import Smartsag.DTO.BasicInformation;
import Smartsag.DTO.DTO;
import Smartsag.DTO.IDInformation;
import Smartsag.DTO.LoginInformation;
import smartsag.Encryption.Encryption;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Oliver
 */
public class ViewCreateNewEmployeeController extends ViewController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtMiddleName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private TextField txtRoleID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    @FXML
    private void createHandler(ActionEvent event) {
        if(!getModel().getCurrentRole().getUserPermissions().canCreate()) {
            showAlert("Du har ikke tilladelse til at oprette en medarbejder-konto!");
            return;
        }
        
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String firstName = txtFirstName.getText();
        String middleName = txtMiddleName.getText();
        String lastName = txtLastName.getText();
        String roleID = txtRoleID.getText();
        
        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || roleID.isEmpty()) {
            showAlert("Du skal indtaste værdier i alle felterne.");
            return;
        }
        
        int numRoleID = 0;
        try {
            numRoleID = Integer.valueOf(roleID);
        } catch(Exception ex) {
            showAlert("Rolle ID'et skal være et tal!");
            return;
        }
        
        // Encrypt the password (Requires rework of the login check to work)
        /*Encryption encryption = new Encryption(getModel().getEncryptionType(), getModel().getEncryptionKey(), getModel().getEncryptionSalt());
        String encryptedPassword = encryption.encryptData(password);*/
        
        DTO employeeDTO = DTO.builder()
                .withLoginInformation(LoginInformation.builder()
                        .username(username)
                        .password(password)
                        .build())
                .withBasicInformation(BasicInformation.builder()
                        .withFirstName(firstName)
                        .withMiddleName(middleName)
                        .withLastName(lastName)
                        .withName(getModel().getCurrentDepartment().getBasicInformation().getName())
                        .build())
                .withIDInformation(IDInformation.getBuilder()
                        .withRoleID(numRoleID)
                        .build())
                .build();
        
        EmployeeDAO employeeDAO = new EmployeeDAO(getModel());
        employeeDAO.create(employeeDTO);
        
        showAlert("Medarbejderen er blevet oprettet!");
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
