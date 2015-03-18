package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jooq.Result;
import services.CustomerCRUDService;

/**
 * FXML Controller class
 *
 * @author Gemtastic
 */
public class ApplicationController implements Initializable {
    
    ListCustomerController customersView;
    
    // Customer tab methods and data
    @FXML
    public BorderPane customerContent;
    
    @FXML
    private Button customerSearch;
    
    @FXML
    private TextField customerSearchField;
    
    @FXML
    private ChoiceBox customerCb;
    
    @FXML
    private void searchCustomer() throws IOException{
        if(customerSearchField.getText().isEmpty()){
            CustomerCRUDService service = new CustomerCRUDService();
            Result<CustomerRecord> customers = service.getAll();
            
            // TODO get the result into the tableView.
            
        }else{
            // send string and checkbox choice into the read() and deligate the result to the view.
            System.out.println("Searching!");
        }
        
        System.out.println(customerContent.getCenter().toString());
        Node node = customerContent.getCenter();
        System.out.println(node);
    }
    
    public void setTabScreen(Node screen, Node tab){
        String setTab = tab.getId();
        System.out.println(setTab);
        
        switch(setTab){
            case "customerContent":
                customerContent.setCenter(screen);
                break;
            default:
                System.out.println("Oops this borked!");
        }
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        customerCb.setItems(FXCollections.observableArrayList("Namn", "Efternamn", "Email", "Telefon", "Kundnr", "Födelseår"));
    }    
    
}
