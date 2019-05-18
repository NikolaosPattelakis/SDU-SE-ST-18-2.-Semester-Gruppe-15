
package View;

import Model.Model;
import View.Controllers.ViewController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIManager {
    private final static String INITIAL_FILEPATH = "FXML/ViewInitial.fxml";
    //private final static String ViewLoginAdmin_FILEPATH = "FXML/ViewLoginAdmin.fxml";
    private final static String LOGIN_BRUGER_FILEPATH = "FXML/ViewLoginBruger.fxml";
    private final static String LOGIN_MEDARBEJDER_FILEPATH = "FXML/ViewLoginMedarbejder.fxml";
    private final static String AKTIVE_BRUGER_FILEPATH = "FXML/ViewAktiveBruger.fxml";
    private final static String AKTIVE_MEDARBEJDER_FILEPATH = "FXML/ViewAktiveMedarbejder.fxml";
    private final static String ANSOEG_FILEPATH = "FXML/ViewAnsoeg.fxml";
    private final static String ANSOEGKRITERIER_FILEPATH = "FXML/ViewAnsoegKriterier.fxml";
    private final static String INAKTIVE_BRUGER_FILEPATH = "FXML/ViewInaktiveBruger.fxml";
    private final static String INAKTIVE_MEDARBEJDER_FILEPATH = "FXML/ViewInaktiveMedarbejder.fxml";
    private final static String MAIN_BRUGER_FILEPATH = "FXML/ViewMainBruger.fxml";
    private final static String MAIN_MEDARBEJDER_FILEPATH = "FXML/ViewMainMedarbejder.fxml";
    private final static String PROFIL_BRUGER_FILEPATH = "FXML/ViewProfilBruger.fxml";
    private final static String CASE_DATABASE_FILEPATH = "FXML/ViewCaseDatabase.fxml";
    private final static String CREATE_CASE_FILEPATH = "FXML/ViewCreateNewCase.fxml";
    private final static String CREATE_EMPLOYEE_FILEPATH = "FXML/ViewCreateNewEmployee.fxml";
    
    private final static String[] FILEPATHS = {
        INITIAL_FILEPATH,
        LOGIN_BRUGER_FILEPATH,
        LOGIN_MEDARBEJDER_FILEPATH,
        AKTIVE_BRUGER_FILEPATH,
        AKTIVE_MEDARBEJDER_FILEPATH,
        ANSOEG_FILEPATH,
        ANSOEGKRITERIER_FILEPATH,
        INAKTIVE_BRUGER_FILEPATH,
        INAKTIVE_MEDARBEJDER_FILEPATH,
        MAIN_BRUGER_FILEPATH,
        MAIN_MEDARBEJDER_FILEPATH,
        PROFIL_BRUGER_FILEPATH,
        CASE_DATABASE_FILEPATH,
        CREATE_CASE_FILEPATH,
        CREATE_EMPLOYEE_FILEPATH
    };
    
    private Model model;
    private Stage currentStage;
    private ViewController currentController;
    private final List<ViewController> viewControllers = new ArrayList<>();
    
    public void Init(Stage stage) {
        currentStage = stage;
        currentStage.setTitle("SmartSag");
        
        model = new Model();
        
        ViewController.setManager(this);
        
        loadControllers();
        loadView(INITIAL_FILEPATH);
        
    }
    
    private void loadControllers(){
        for (String path : FILEPATHS) {
            try {
                FXMLLoader loader = new FXMLLoader();
                Scene scene = new Scene(loader.load(getClass().getResource(path).openStream()));
                ViewController controller = (ViewController) loader.getController();
                
                if (controller != null) {
                    controller.setPath(path);
                    controller.setScene(scene);
                    viewControllers.add(controller);
                }
            } catch (IOException ex) {
                Logger.getLogger(GUIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loadView(String FXMLFile) {
        for (ViewController controller : viewControllers) {
            if (controller.getPath().equals(FXMLFile)) {
                controller.onViewInit();
                
                currentStage.setScene(controller.getScene());
                currentStage.setResizable(false);
                currentController = controller;
                currentController.getScene().getRoot().requestFocus();
                break;
            }
        }
        currentStage.show();
    }
    
    public void setCurrentController(ViewController controller) {
        currentController = controller;
    }
    
    public List<ViewController> getControllers() {
        return this.viewControllers;
    }
    
    public Model getModel() {
        return model;
    }
    
    public void setStage(Stage stage) {
        currentStage = stage;
    }
    public Stage getStage() {
        return currentStage;
    }
    
    public String getInitialPath() {
        return INITIAL_FILEPATH;
    }
    
    public String getLoginBrugerPath() {
        return LOGIN_BRUGER_FILEPATH;
    }
    
    public String getLoginMedarbejderPath() {
        return LOGIN_MEDARBEJDER_FILEPATH;
    }
    
    public String getAktiveBrugerPath() {
        return AKTIVE_BRUGER_FILEPATH;
    }
    
    public String getAktiveMedarbejderPath() {
        return AKTIVE_MEDARBEJDER_FILEPATH;
    }
    
    public String getAnsoegPath() {
        return ANSOEG_FILEPATH;
    }
    
    public String getAnsoegKriterierPath() {
        return ANSOEGKRITERIER_FILEPATH;
    }
    
    public String getInaktiveBrugerPath() {
        return INAKTIVE_BRUGER_FILEPATH;
    }
    
    public String getInaktiveMedarbejderPath() {
        return INAKTIVE_MEDARBEJDER_FILEPATH;
    }
    
    public String getMainBrugerPath() {
        return MAIN_BRUGER_FILEPATH;
    }
    
    public String getMainMedarbejderPath() {
        return MAIN_MEDARBEJDER_FILEPATH;
    }
    
    public String getProfilBrugerPath() {
        return PROFIL_BRUGER_FILEPATH;
    }
    
    public String getCaseDatabasePath() {
        return CASE_DATABASE_FILEPATH;
    }
    
    public String getCreateNewCasePath() {
        return CREATE_CASE_FILEPATH;
    }
    
    public String getCreateNewEmployeePath() {
        return CREATE_EMPLOYEE_FILEPATH;
    }
}
