package Controller.customers;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.AddressSearchService;

/**
 *
 * @author Gemtastic
 */
public class DisplayCustomerController implements Initializable {
    
    private CustomerRecord customer;

    @FXML
    private Label name;
    
    @FXML
    private Label age;
    
    @FXML
    private Label gender;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label email;
    
    @FXML
    private Label customerId;
    
    @FXML
    private Label street;
    
    @FXML
    private Label co;
    
    @FXML
    private Label city;
    
    @FXML
    private Label zip;
    
    public void cleanSlate(){
        this.name.setVisible(false);
        this.age.setVisible(false);
        this.gender.setVisible(false);
        this.phone.setVisible(false);
        this.email.setVisible(false);
        this.customerId.setVisible(false);
        this.street.setVisible(false);
        this.co.setVisible(false);
        this.city.setVisible(false);
        this.zip.setVisible(false);
    }
    
    public void loadCustomer(CustomerRecord customer){
        this.customer = customer;
        
        AddressSearchService service = new AddressSearchService();
        AddressRecord address = service.getAllWhere("id", String.valueOf(customer.getId()));
        
        this.name.setText(customer.getFirstName() + " " + customer.getLastName());
        this.phone.setText(customer.getPhone());
        this.customerId.setText(String.valueOf(customer.getId()));
        this.email.setText(customer.getEmail());
        this.street.setText(address.getStreet());
        if(address.getCo() == null){
            // do nothing
        }else{
            this.co.setText(address.getCo());
            this.co.setVisible(true);
        }
        this.city.setText(address.getCity());
        this.zip.setText(String.valueOf(address.getZip()));
        
        // Todo add age
        
        String g = customer.getGender().toLowerCase();
        
        switch(g){
            case "f":
                this.gender.setText("Kvinna");
                break;
            case "m":
                this.gender.setText("Man");
                break;
            default:
                this.gender.setText("Hen");
                break;
        }
        
        this.name.setVisible(true);
//        this.age.setVisible(true);
        this.gender.setVisible(true);
        this.phone.setVisible(true);
        this.email.setVisible(true);
        this.customerId.setVisible(true);
        this.street.setVisible(true);
        this.city.setVisible(true);
        this.zip.setVisible(true);
        
    }
    
    @FXML
    private void editCustomer(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.editCustomers,
                                            ApplicationNavigator.controller.customerContent,
                                            ApplicationNavigator.editCustomersController);
        ApplicationNavigator.editCustomersController.loadCustomer(customer);
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
