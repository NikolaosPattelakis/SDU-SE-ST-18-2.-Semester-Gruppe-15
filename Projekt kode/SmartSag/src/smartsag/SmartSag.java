package smartsag;

import DAO.CitizenDAO;
import DAO.EmployeeDAO;
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

        CitizenDAO login = new CitizenDAO(model);
        String username= "123456789";
        String password="kode123";
        System.out.println(model.getCurrentDepartment().getBasicInformation().getName());
        login.login(username, password);
        System.out.println(model.getCurrentUser().getBasicInformation().getCPR());
        System.out.println(model.getCurrentUser().getBasicInformation().getFirstName());
        
        username="nipat";
        password="kode1234";
        EmployeeDAO login2 = new EmployeeDAO(model);
        login2.login(username, password);
        System.out.println(model.getCurrentUser().getBasicInformation().getFirstName());
    }

}
