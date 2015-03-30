package Controller.customers;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CRUD.AddressCRUDService;
import services.CRUD.CustomerCRUDService;

/**
 *
 * @author Aizic Moisen
 */
public class AddCustomerController implements Initializable{
    
    @FXML
    private Label errorMessage;
    
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
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, 
                                            ApplicationNavigator.controller.customerContent, 
                                            ApplicationNavigator.listCustomersController);
    }
    
    @FXML
    private void addCustomer(){
        
        errorMessage.setVisible(false);
        
        CustomerCRUDService customerService = new CustomerCRUDService();
        AddressCRUDService addressService = new AddressCRUDService();
        
        CustomerRecord customer = new CustomerRecord();
        AddressRecord address = new AddressRecord();
        
        // This does not ensure that the zip is a valid zip or number
        if(gender.getSelectionModel().getSelectedItem() != null && dateOfBirth.getValue() != null &&
                lastName.getText() != null && firstName.getText() != null && phone.getText() != null &&
                email.getText() != null && street.getText() != null && city.getText() != null &&
                zip.getText() != null){
            address.setCity(city.getText());
            address.setStreet(street.getText());
            
            try{
            address.setZip(Integer.parseInt(zip.getText()));
            }catch(NumberFormatException e){
                e.printStackTrace();
                errorMessage.setVisible(true);
            }
            
            if(co.getText() != null){
                address.setCo(co.getText());
            }
            
            if(address.getZip() != null){
                AddressRecord a = addressService.create(address);
                customer.setAddress(a.getId());
                Date date = Date.valueOf(dateOfBirth.getValue());
                customer.setDateOfBirth(date);
                customer.setEmail(email.getText());
                customer.setFirstName(firstName.getText());
                customer.setGender(gender.getSelectionModel().getSelectedItem().toString());
                customer.setLastName(lastName.getText());
                customer.setPhone(phone.getText());
                
                customerService.create(customer);
                
                ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, 
                                                    ApplicationNavigator.controller.customerContent, 
                                                    ApplicationNavigator.listCustomersController);
            }else{
                errorMessage.setVisible(true);
            }
            
        }else{
            errorMessage.setVisible(true);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.setItems(FXCollections.observableArrayList("M", "F", "H"));
        errorMessage.setVisible(false);
    }
    
}
