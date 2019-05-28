package smartsag.Controller.FXMLControllers;

import Model.DAO.CitizenDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import smartsag.Encryption.Encryption;

/*
    FXML Controller class
*/
public class ViewLoginBrugerController extends ViewController implements Initializable {

    @FXML
    private TextField txtCPR;
    
    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @Override
    public void onViewInit() {
        
    }
    
    /*
        Attempts to log in to a citizen account based on the entered CPR and password information.
    */
    @FXML
    private void proceedHandler(ActionEvent event) {
        String cpr = txtCPR.getText();
        String password = txtPassword.getText();
        
        if(cpr.isEmpty() || password.isEmpty()) {
            showAlert("Du skal indtaste både CPR-nummer og kodeord.");
            return;
        }
        
        int numCPR = 0;
        try {
            numCPR = Integer.valueOf(cpr);
        } catch(Exception ex) {
            showAlert("Dit CPR-nummer skal udelukkende bestå af tal.");
            return;
        }
        
        Encryption encryption = new Encryption(getModel().getEncryptionType(), getModel().getEncryptionKey(), getModel().getEncryptionSalt());
        String encryptedPassword = encryption.encryptData(password);
        
        CitizenDAO citizen = new CitizenDAO(getModel());
        citizen.login(cpr, encryptedPassword);
        
        if(getModel().getCurrentUser().getIDInformation() == null) {
            showAlert("Login mislykkedes, check dine login-oplysninger.");
        }
        else {
            guiManager.loadView(guiManager.getMainBrugerPath());
        }
    }
    
    @FXML
    private void backHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
    
}