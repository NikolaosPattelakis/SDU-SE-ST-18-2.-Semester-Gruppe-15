/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartsag.Controller.FXMLControllers;

import Smartsag.DTO.BasicInformation;
import smartsag.DTO.enums.Gender;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
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
    private TextField txtCPR;
    
    @FXML
    private TextField txtGender;
    
    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtMiddleName;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void onViewInit() {
        BasicInformation info = getModel().getCurrentUser().getBasicInformation();
        
        txtCPR.setText(String.valueOf(info.getCPR()));
        txtGender.setText((info.getGender() == Gender.male) ? "Mand" : "Kvinde");
        txtFirstName.setText(info.getFirstName());
        txtMiddleName.setText(info.getMiddleName());
        txtLastName.setText(info.getLastName());
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