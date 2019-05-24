
package smartsag.Controller.FXMLControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class ViewInitialController extends ViewController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void onViewInit() {
        
    }
    
    @FXML
    private void handleCitizen(ActionEvent event) {
        guiManager.loadView(guiManager.getLoginBrugerPath());
    }
    
    @FXML
    private void handleEmployee(ActionEvent event) {
        guiManager.loadView(guiManager.getLoginMedarbejderPath());
    }
}