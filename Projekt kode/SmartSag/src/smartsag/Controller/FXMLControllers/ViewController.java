
package smartsag.Controller.FXMLControllers;

import Model.Persistence;
import javafx.scene.Scene;
import smartsag.Controller.GUIManager;
import View.ViewEvents;
import javafx.scene.control.Alert;

public abstract class ViewController extends ViewEvents {
    
    protected static GUIManager guiManager;
    private Scene scene;
    private String path;
    
    public static void setManager(GUIManager gui) {
        guiManager = gui;
    }
    
    public Persistence getModel() {
        return guiManager.getModel();
    }
    
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
