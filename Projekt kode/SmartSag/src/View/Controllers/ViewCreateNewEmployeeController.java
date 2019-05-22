/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Controllers;

import DAO.EmployeeDAO;
import DTO.BasicInformation;
import DTO.DTO;
import DTO.IDInformation;
import DTO.LoginInformation;
import Encryption.Encryption;
import static View.Controllers.ViewController.guiManager;
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
    private TextField txtLastName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    @FXML
    private void createHandler(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        
        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            showAlert("Du skal indstate et brugernavn, kodeord, fornavn og efternavn på medarbejderen.");
            return;
        }
        
        // Encrypt the password
        Encryption encryption = new Encryption(getModel().getEncryptionType(), getModel().getEncryptionKey(), getModel().getEncryptionSalt());
        String encryptedPassword = encryption.encryptData(password);
        
        DTO employeeDTO = DTO.builder()
                .withLoginInformation(LoginInformation.builder()
                        .username(username)
                        .password(encryptedPassword)
                        .build())
                .withBasicInformation(BasicInformation.builder()
                        .withFirstName(firstName)
                        .withMiddleName("")
                        .withLastName(lastName)
                        .build())
                .withIDInformation(IDInformation.getBuilder()
                        .withRoleID(1)
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
