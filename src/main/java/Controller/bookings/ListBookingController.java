package Controller.bookings;

import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jooq.Result;
import services.CRUD.CarCRUDService;
import util.JavafxUtils;

/**
 *
 * @author Aizic Moisen
 */
public class ListBookingController implements Initializable{

    @FXML
    public TableView<AppointmentsRecord> bookings;

    @FXML
    private TableColumn<AppointmentsRecord, Integer> bookingId;
    
    @FXML
    private TableColumn<AppointmentsRecord, Timestamp> date;
    
    @FXML
    private TableColumn<AppointmentsRecord, String> type;
    
    @FXML
    private TableColumn<CarRecord, String> vehicle;
    
    @FXML
    private TableColumn<CustomerRecord, String> customer;
    
    @FXML
    private TableColumn<EmployeesRecord, Integer> mechanic;
    
    public void populateTable(Result<AppointmentsRecord> record){
        if (record == null) {
            ObservableList<AppointmentsRecord> list = FXCollections.observableArrayList();
            bookings.setItems(list);
        } else {
            JavafxUtils.setColumnValue(bookingId, AppointmentsRecord::getId);
            JavafxUtils.setColumnValue(type, AppointmentsRecord::getType);
            JavafxUtils.setColumnValue(vehicle, CarRecord::getLicensePlate);
            JavafxUtils.setColumnValue(customer, CustomerRecord::getPhone);
            JavafxUtils.setColumnValue(mechanic, EmployeesRecord::getId);
            JavafxUtils.setColumnValue(date, AppointmentsRecord::getScheduledDate);

            ObservableList<AppointmentsRecord> list = FXCollections.observableArrayList();

            for (AppointmentsRecord r : record) {
                list.add(r);
            }
            bookings.setItems(list);
        }
    }
    
    public AppointmentsRecord getSelected(){
        AppointmentsRecord r = bookings.getSelectionModel().getSelectedItem();
        return r;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
