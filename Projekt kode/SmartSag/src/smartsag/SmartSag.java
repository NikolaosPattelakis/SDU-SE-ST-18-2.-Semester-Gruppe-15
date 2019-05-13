package smartsag;

import Domain.Controller;
import Model.Model;
import View.View;
import View.ViewBuilder;

public class SmartSag {

    /**
     * Main class <br>
     * Currently used for testing.
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        View view = new ViewBuilder().createView();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        
    }

}
