package Controller.customers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author Aizic Moisen
 */
public class AddCustomerController implements Initializable{
    
    @FXML
    private ChoiceBox gender;
    
    @FXML
    private DatePicker dateOfBirth;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField lastName;
    
    @FXML
    private TextField phone;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField street;
    
    @FXML
    private TextField co;
    
    @FXML
    private TextField city;
    
    @FXML
    private TextField zip;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    
    @FXML
    private void cancelAdd(){
        
    }
    
    @FXML
    private void addCustomer(){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
