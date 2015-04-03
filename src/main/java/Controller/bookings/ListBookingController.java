package Controller.bookings;

import AlternativeRecords.AppointmentBookingRecord;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jooq.Result;
import services.CRUD.AppointmentCRUDService;
import services.CRUD.CarCRUDService;
import services.CRUD.CustomerCRUDService;
import util.JavafxUtils;

/**
 *
 * @author Aizic Moisen
 */
public class ListBookingController implements Initializable{

    @FXML
    public TableView<AppointmentBookingRecord> bookings;

    @FXML
    private TableColumn<AppointmentBookingRecord, Integer> bookingId;
    
    @FXML
    private TableColumn<AppointmentBookingRecord, String> date;
    
    @FXML
    private TableColumn<AppointmentBookingRecord, String> type;
    
    @FXML
    private TableColumn<AppointmentBookingRecord, String> vehicle;
    
    @FXML
    private TableColumn<AppointmentBookingRecord, String> customer;
    
    @FXML
    private TableColumn<AppointmentBookingRecord, Integer> mechanic;
    
    public void populateTable(List<AppointmentBookingRecord> record){
        if (record == null || record.isEmpty()) {
            ObservableList<AppointmentBookingRecord> list = FXCollections.observableArrayList();
            bookings.setItems(list);
        } else {
            JavafxUtils.setColumnValue(bookingId, AppointmentBookingRecord::getBookingId);
            JavafxUtils.setColumnValue(type, AppointmentBookingRecord::getType);
            JavafxUtils.setColumnValue(vehicle, AppointmentBookingRecord::getVehicle);
            JavafxUtils.setColumnValue(customer, AppointmentBookingRecord::getCustomer);
            JavafxUtils.setColumnValue(mechanic, AppointmentBookingRecord::getMechanic);
            JavafxUtils.setColumnValue(date, AppointmentBookingRecord::getDate);

            ObservableList<AppointmentBookingRecord> list = FXCollections.observableArrayList();

            for (AppointmentBookingRecord r : record) {
                list.add(r);
            }
            bookings.setItems(list);
        }
    }
    
    public AppointmentsRecord getSelected(){
        AppointmentCRUDService service = new AppointmentCRUDService();
        AppointmentsRecord r = null;
        
        AppointmentBookingRecord selected = bookings.getSelectionModel().getSelectedItem();
        
        if(selected != null){
            r = service.read(selected.getBookingId());
        }
        
        
        return r;
    }
    
    public List<AppointmentBookingRecord> getAsBookingList(Result<AppointmentsRecord> records){
        List<AppointmentBookingRecord> b = FXCollections.observableArrayList();
        
        CarCRUDService carS = new CarCRUDService();
        CustomerCRUDService customerS = new CustomerCRUDService();
        
        if(records != null && records.isNotEmpty()){
            for(AppointmentsRecord r : records){
                CarRecord car = carS.read(r.getCar());
                CustomerRecord cust = customerS.read(r.getCommissioner());

                AppointmentBookingRecord rec = new AppointmentBookingRecord(r, car, cust);
                b.add(rec);
            }
        }
        
        return b;
    } 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
