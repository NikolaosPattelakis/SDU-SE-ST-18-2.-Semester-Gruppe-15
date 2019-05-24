package smartsag;

import smartsag.Controller.GUIManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class SmartSag extends Application {
    
    private GUIManager guiManager;
    
    @Override
    public void start(Stage stage) throws Exception {
        guiManager = new GUIManager();
        guiManager.Init(stage);
    }
    
    public static void main(String[] args) {

        launch(args);
       
    }

}
