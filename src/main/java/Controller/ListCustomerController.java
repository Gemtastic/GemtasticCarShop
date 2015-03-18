package Controller;

import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jooq.Result;
import util.JavafxUtils;

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
    private TableColumn<CustomerRecord, Integer> customerID;

    @FXML
    private TableColumn<CustomerRecord, Date> dateOfBirth;

    public void populateTable(Result<CustomerRecord> record) {
        
        JavafxUtils.setColumnValue(firstname, CustomerRecord::getFirstName);
        JavafxUtils.setColumnValue(lastname, CustomerRecord::getLastName);
        JavafxUtils.setColumnValue(email, CustomerRecord::getEmail);
        JavafxUtils.setColumnValue(phone, CustomerRecord::getPhone);
        JavafxUtils.setColumnValue(customerID, CustomerRecord::getId);
        JavafxUtils.setColumnValue(dateOfBirth, CustomerRecord::getDateOfBirth);
        
        ObservableList<CustomerRecord> list = FXCollections.observableArrayList();

        for (CustomerRecord r : record) {
            list.add(r);
        }
        customers.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        try(Connection con = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "g3mt45t1c")){
//            DSLContext jooq = DSL.using(con, SQLDialect.POSTGRES);
//            
//        JavafxUtils.setColumnValue(firstname, CustomerRecord::getFirstName);
//        JavafxUtils.setColumnValue(lastname, CustomerRecord::getFirstName);
//        JavafxUtils.setColumnValue(email, CustomerRecord::getFirstName);
//        JavafxUtils.setColumnValue(phone, CustomerRecord::getFirstName);
//        JavafxUtils.setColumnValue(customerID, CustomerRecord::getFirstName);
//        JavafxUtils.setColumnValue(dateOfBirth, CustomerRecord::getFirstName);
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
