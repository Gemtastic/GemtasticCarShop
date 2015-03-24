package Controller.employees;

import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import services.CRUD.EmployeeCRUDService;
import services.LogInService;

/**
 *
 * @author Gemtastic
 */
public class AddEmployeeController implements Initializable{

    @FXML
    private Label username;
    
    @FXML
    private Label email;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label passwordErrorMessage;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField repeatPassword;
    
    @FXML
    private Button addUserBtn;
    
    @FXML
    private Button cancelUserBtn;
    
    private StringProperty passwordMatch = new SimpleStringProperty();
    
    
    @FXML
    private void addUser(){
        passwordErrorMessage.setVisible(false);
        
        EmployeesRecord record = new EmployeesRecord();
        EmployeeCRUDService service = new EmployeeCRUDService();
        LogInService hashService = new LogInService();
        
        if(repeatPassword.getText().equals(password.getText())){
        
        record.setEmail(email.getText());
        record.setUsername(username.getText());
        record.setPhone(phone.getText());
        
        String hashedPW = hashService.hash(password.getText());
        
        record.setPassword(hashedPW);
        
        
        }else{
            passwordErrorMessage.setVisible(true);
        }
    }
    
    @FXML
    private void cancelUser(){
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
