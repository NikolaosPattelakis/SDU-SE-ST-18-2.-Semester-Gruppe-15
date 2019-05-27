package smartsag.Controller.FXMLControllers;

import Model.DAO.CaseDAO;
import Smartsag.DTO.DTO;
import smartsag.DTO.enums.CaseStatus;
import static smartsag.Controller.FXMLControllers.ViewController.guiManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/*
 * FXML Controller class
 */
public class ViewInaktiveBrugerController extends ViewController implements Initializable {
    
    @FXML
    private ListView listInactiveCases;
    
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    /*
        Reads all the cases relevant for the current citizen and shows all the closed cases with their ID in a ListView.
    */
    @Override
    public void onViewInit() {
        CaseDAO caseDAO = new CaseDAO(getModel());
        List<DTO> cases = caseDAO.readAll();
        
        observableList.clear();
        for(DTO dto : cases) {
            if(dto.getCaseStatus() != CaseStatus.closed) {
                continue;
            }
            
            observableList.add("Case - ID: " + dto.getIDInformation().getID());
        }
        
        listInactiveCases.setItems(observableList);
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