
package View.Controllers;

import static View.Controllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ViewLoginMedarbejderController extends ViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void proceedHandler(ActionEvent event) {
        //Midlertidig for tests.
        guiManager.loadView(guiManager.getMainMedarbejderPath());
    }
    
    @FXML
    private void backHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
}