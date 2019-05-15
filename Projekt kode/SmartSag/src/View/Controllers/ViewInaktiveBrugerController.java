/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Controllers;

import static View.Controllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Oliver
 */
public class ViewInaktiveBrugerController extends ViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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