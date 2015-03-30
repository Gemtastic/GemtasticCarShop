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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.CRUD.EmployeeCRUDService;

/**
 *
 * @author Gemtastic
 */
public class EditEmployeeController implements Initializable{
    
    private EmployeesRecord employee;
    
    @FXML
    private Label errorMsg;
    
    @FXML
    private Label missmatchError;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField phone;
    
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField repeatPassword;
    
    @FXML
    private ListView auctorisationList;
    
    @FXML
    private ListView makeList;
    
    @FXML
    private Button deleteMakeBtn;
    
    @FXML
    private Button addMakeBtn;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private void save(){
        missmatchError.setVisible(false);
        errorMsg.setVisible(false);
        
        if(password.getText().equals(repeatPassword.getText()) && password.getText() != null){
            EmployeeCRUDService update = new EmployeeCRUDService();
        
            employee.setEmail(email.getText());
            employee.setPhone(phone.getText());
            employee.setUsername(username.getText());
            employee.setPassword(password.getText());

            boolean success = update.update(employee);

            if(success){
                ApplicationNavigator.loadTabContent(ApplicationNavigator.employee,
                                                    ApplicationNavigator.controller.employeeContent,
                                                    ApplicationNavigator.displayCustomersController);
                ApplicationNavigator.displayEmployeeController.loadEmployee(employee);
            }else{
                errorMsg.setVisible(true);
            }
        }else{
            missmatchError.setVisible(true);
        }
    }
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.employee,
                                            ApplicationNavigator.controller.employeeContent,
                                            ApplicationNavigator.displayCustomersController);
        ApplicationNavigator.displayEmployeeController.loadEmployee(employee);
    }
    
    @FXML
    private void deleteMake(){
        
    }
    
    @FXML
    private void addMake(){
        
    }
    
    public void loadEmployee(EmployeesRecord employee){
        this.employee = employee;
        
        username.setText(employee.getUsername());
        email.setText(employee.getEmail());
        phone.setText(employee.getPhone());
        
        // TODO fill in the ListViews
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setVisible(false);
        missmatchError.setVisible(false);
    }
    
}
