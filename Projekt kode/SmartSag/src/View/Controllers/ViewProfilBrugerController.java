/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Controllers;

import DTO.BasicInformation;
import static View.Controllers.ViewController.guiManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Oliver
 */
public class ViewProfilBrugerController extends ViewController implements Initializable {
    
    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private TextField txtAddress;
    
    @FXML
    private TextField txtZipCode;
    
    @FXML
    private TextField txtCity;
    
    @FXML
    private TextField txtCountry;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtPhoneNumber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void onViewInit() {
        BasicInformation info = getModel().getCurrentUser().getBasicInformation();
        txtFirstName.setText(info.getFirstName());
        txtLastName.setText(info.getLastName());
        txtEmail.setText(info.getEmail());
        txtPhoneNumber.setText(info.getPhoneNumber());
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