package smartsag.Controller.FXMLControllers;

import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/*
 * FXML Controller class
 */
public class ViewMainBrugerController extends ViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    @FXML
    private void mainMenuHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getMainBrugerPath());
    }
    
    @FXML
    private void profileHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getProfilBrugerPath());
    }
    
    @FXML
    private void applyHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getAnsoegKriterierPath());
    }
    
    @FXML
    private void activeHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getAktiveBrugerPath());
    }
    
    @FXML
    private void inactiveHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInaktiveBrugerPath());
    }
    
    @FXML
    private void logoutHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
    
}