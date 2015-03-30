package Controller.employees;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author Gemtastic
 */
public class DisplayEmployeeController implements Initializable {
    
    private EmployeesRecord employee;
    
    @FXML
    private Label username;
    
    @FXML
    private Label id;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label email;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private ListView auctorisationList;
    
    public void loadEmployee(EmployeesRecord employee){
        this.employee = employee;
        username.setText(employee.getUsername());
        id.setText(String.valueOf(employee.getId()));
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail());
    }
    
    @FXML
    public void editEmployee(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.editEmployees, 
                                            ApplicationNavigator.controller.employeeContent, 
                                            ApplicationNavigator.editEmployeeController);
        ApplicationNavigator.editEmployeeController.loadEmployee(employee);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
