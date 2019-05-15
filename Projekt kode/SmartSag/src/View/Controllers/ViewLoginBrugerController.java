
package View.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ViewLoginBrugerController extends ViewController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void proceedHandler(ActionEvent event) {
        //Midlertidig for tests.
        guiManager.loadView(guiManager.getMainBrugerPath());
    }
    
    @FXML
    private void backHandler(ActionEvent event) {
        guiManager.loadView(guiManager.getInitialPath());
    }
    
}