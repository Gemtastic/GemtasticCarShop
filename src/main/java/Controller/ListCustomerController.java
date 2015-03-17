/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.gemtastic.carshop.tables.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Gemtastic
 */
public class ListCustomerController implements Initializable {
    
    @FXML
    private TableView<Customer> customers;
    
    @FXML
    private TableColumn<Customer, String> firstname;
    
    @FXML
    private TableColumn<Customer, String> lastname;
    
    @FXML
    private TableColumn<Customer, String> address;
    
    @FXML
    private TableColumn<Customer, String> phone;
    
    @FXML
    private TableColumn<Customer, String> customerID;
    
    @FXML
    private TableColumn<Customer, String> dateOfBirth;
    
    public void populateTable(ObservableList<Customer> customer){
        customers.setItems(customer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
