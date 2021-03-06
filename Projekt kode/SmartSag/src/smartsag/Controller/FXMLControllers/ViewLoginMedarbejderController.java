package smartsag.Controller.FXMLControllers;

import Model.DAO.EmployeeDAO;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import smartsag.Encryption.Encryption;

/**
 * FXML Controller class
*/
public class ViewLoginMedarbejderController extends ViewController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    /**
      *  Attempts to log in to an employee account based on the entered username and password information.
    */
    @FXML
    private void proceedHandler(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        if(username.isEmpty() || password.isEmpty()) {
            showAlert("Du skal indtaste både brugernavn og kodeord.");
            return;
        }
        
        Encryption encryption = new Encryption(getModel().getEncryptionType(), getModel().getEncryptionKey(), getModel().getEncryptionSalt());
        String encryptedPassword = encryption.encryptData(password);
        
        EmployeeDAO employee = new EmployeeDAO(getModel());
        employee.login(username, encryptedPassword);
        
        if(getModel().getCurrentUser().getIDInformation() == null) {
            showAlert("Login mislykkedes, check dine login-oplysninger.");
        }
        else {
            guiManager.loadView(guiManager.getMainMedarbejderPath());
        }
    }
    
    @FXML
    private void backHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
}