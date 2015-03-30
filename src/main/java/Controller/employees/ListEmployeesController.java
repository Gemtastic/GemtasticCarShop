package Controller.employees;

import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.net.URL;
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
 * @author Aizic Moisen
 */
public class ListEmployeesController  implements Initializable{
    
    @FXML
    public TableView<EmployeesRecord> employees;
    
    @FXML
    public TableColumn<EmployeesRecord, Integer> employeeId;
    
    @FXML
    public TableColumn<EmployeesRecord, String> email;
    
    @FXML
    public TableColumn<EmployeesRecord, String> phone;
    
    @FXML
    public TableColumn<EmployeesRecord, String> username;
    
    public void populateTable(Result<EmployeesRecord> record){
        if (record == null) {
            ObservableList<EmployeesRecord> list = FXCollections.observableArrayList();
            employees.setItems(list);
        } else {
            JavafxUtils.setColumnValue(username, EmployeesRecord::getUsername);
            JavafxUtils.setColumnValue(email, EmployeesRecord::getEmail);
            JavafxUtils.setColumnValue(phone, EmployeesRecord::getPhone);
            JavafxUtils.setColumnValue(employeeId, EmployeesRecord::getId);

            ObservableList<EmployeesRecord> list = FXCollections.observableArrayList();

            for (EmployeesRecord r : record) {
                list.add(r);
            }
            employees.setItems(list);
        }
    }
    
    public EmployeesRecord getSelected(){
        EmployeesRecord employee = employees.getSelectionModel().getSelectedItem();
        
        return employee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
