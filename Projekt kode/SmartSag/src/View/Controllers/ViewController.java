
package View.Controllers;

import javafx.scene.Scene;
import View.GUIManager;

public abstract class ViewController {
    
    protected static GUIManager guiManager;
    private Scene scene;
    private String path;
    
    public static void setManager(GUIManager gui) {
        guiManager = gui;
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
    
}
