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
public class ViewMainMedarbejderController extends ViewController implements Initializable {

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