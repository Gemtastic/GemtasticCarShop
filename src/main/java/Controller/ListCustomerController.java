/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.util.JavafxUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author Gemtastic
 */
public class ListCustomerController implements Initializable {
    
    @FXML
    public TableView<CustomerRecord> customers;
    
    @FXML
    public TableColumn<CustomerRecord, String> firstname;
    
    @FXML
    private TableColumn<CustomerRecord, String> lastname;
    
    @FXML
    private TableColumn<CustomerRecord, String> email;
    
    @FXML
    private TableColumn<CustomerRecord, String> phone;
    
    @FXML
    private TableColumn<CustomerRecord, String> customerID;
    
    @FXML
    private TableColumn<CustomerRecord, String> dateOfBirth;
    
    public void populateTable(){
                try(Connection con = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "g3mt45t1c")){
            DSLContext jooq = DSL.using(con, SQLDialect.POSTGRES);
            
            ObservableList<CustomerRecord> l = customers.getItems();
            
            for(CustomerRecord r : jooq.fetch(CUSTOMER)){
                l.add(r);
            }
            customers.setItems(l);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
//        try(Connection con = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "g3mt45t1c")){
//            DSLContext jooq = DSL.using(con, SQLDialect.POSTGRES);
//            
            JavafxUtils.setColumnValue(firstname, CustomerRecord::getFirstName);
            JavafxUtils.setColumnValue(lastname, CustomerRecord::getFirstName);
            JavafxUtils.setColumnValue(email, CustomerRecord::getFirstName);
            JavafxUtils.setColumnValue(phone, CustomerRecord::getFirstName);
            JavafxUtils.setColumnValue(customerID, CustomerRecord::getFirstName);
            JavafxUtils.setColumnValue(dateOfBirth, CustomerRecord::getFirstName);
//            
//            ObservableList<CustomerRecord> l = customers.getItems();
//            
//            for(CustomerRecord r : jooq.fetch(CUSTOMER)){
//                l.add(r);
//            }
//            customers.setItems(l);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}
