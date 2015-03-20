package Controller.customers;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.AddressCRUDService;
import services.AddressSearchService;
import services.CustomerCRUDService;

/**
 *
 * @author Aizic Moisen
 */
public class EditCustomerController implements Initializable{
    
    private CustomerRecord customer;
    private AddressRecord address;
    
    @FXML
    private Label error;
    
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

    public void loadCustomer(CustomerRecord customer){
        this.customer = customer;
        AddressCRUDService service = new AddressCRUDService();
        this.address = service.read(customer.getAddress());
        
        this.firstName.setText(customer.getFirstName());
        this.lastName.setText(customer.getLastName());
        this.phone.setText(customer.getPhone());
        this.email.setText(customer.getEmail());
        this.street.setText(address.getStreet());
        this.city.setText(address.getCity());
        this.zip.setText(String.valueOf(address.getZip()));
        
        if(address.getCo() != null){
            this.co.setText(address.getCo());
        }
    }
    
    @FXML
    private void saveEdit(){
        
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setPhone(phone.getText());
        customer.setEmail(email.getText());
        address.setStreet(street.getText());
        address.setCity(city.getText());
        address.setZip(Integer.parseInt(zip.getText()));
        
        if(this.co.getText() != null){
            address.setCo(this.co.getText());
        }
        
        CustomerCRUDService customerService = new CustomerCRUDService();
        AddressCRUDService addressService = new AddressCRUDService();
        
        boolean successCustomer = customerService.update(customer);
        boolean successAddress = addressService.update(address);
        
        if(successAddress && successCustomer){
            ApplicationNavigator.loadTabContent(ApplicationNavigator.customer,
                                                ApplicationNavigator.controller.customerContent,
                                                ApplicationNavigator.displayCustomersController);
            ApplicationNavigator.displayCustomersController.loadCustomer(customer);
        }else{
            error.setVisible(true);
        }
    }
    
    @FXML
    private void cancelEdit(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.customer,
                                                ApplicationNavigator.controller.customerContent,
                                                ApplicationNavigator.displayCustomersController);
        ApplicationNavigator.displayCustomersController.loadCustomer(customer);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
