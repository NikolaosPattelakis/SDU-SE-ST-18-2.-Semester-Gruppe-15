package smartsag;

import Controller.Controller;
import Model.Model;
import Model.POJO.POJO;
import Model.Persistence.DAO;
import Model.Persistence.POJOType;
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
    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        DAO dao = new DAO();

        String username1 = "kasper12";
        String password1 = "kode123";
        
        String username2 = "Ulysees";
        String password2 = "noone";
        
        if (model.authenticateUser(username1, password1) == true) {
            POJO pojo = model.getUser();

            System.out.println(pojo.getID());
            System.out.println(pojo.getCPR());
            System.out.println(pojo.getLoginInformation().getUsername());
            System.out.println(pojo.getLoginInformation().getPassword());
            System.out.println(pojo.getBasicInformation().getFirstName());
            System.out.println(pojo.getBasicInformation().getMiddleName());
            System.out.println(pojo.getBasicInformation().getLastName());
            System.out.println(pojo.getBasicInformation().getGender());
        }
    }

}
