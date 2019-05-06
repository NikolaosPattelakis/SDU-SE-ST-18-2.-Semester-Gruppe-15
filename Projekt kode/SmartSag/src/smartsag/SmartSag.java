package smartsag;

import Controller.Controller;
import Model.Model;
import View.View;

public class SmartSag {

    /**
     * Main class <br>
     * Currently used for testing.
     *
     *
     *
     * @param args
     */
    public static void main(String[] args){

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        
        model.getUser();
    }
    
}
