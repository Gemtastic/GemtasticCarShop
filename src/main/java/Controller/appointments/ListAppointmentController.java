package Controller.appointments;

import AlternativeRecords.AppointmentAppointmentsRecord;
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
import services.CRUD.CarCRUDService;
import services.CRUD.CustomerCRUDService;
import util.JavafxUtils;

/**
 *
 * @author Aizic Moisen
 */
public class ListAppointmentController implements Initializable{

    
    @FXML
    public TableView<AppointmentAppointmentsRecord> appointment;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, Integer> appointmentId;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, String> date;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, String> type;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, String> appointedVehicle;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, String> payingCustomer;
    
    @FXML
    public TableColumn<AppointmentAppointmentsRecord, Integer> mechanic;
    
    public void populateTable(List<AppointmentAppointmentsRecord> record){
        
        if (record == null || record.isEmpty()) {
            ObservableList<AppointmentAppointmentsRecord> list = FXCollections.observableArrayList();
            appointment.setItems(list);
        } else {
            JavafxUtils.setColumnValue(appointmentId, AppointmentAppointmentsRecord::getId);
            JavafxUtils.setColumnValue(type, AppointmentAppointmentsRecord::getType);
            JavafxUtils.setColumnValue(appointedVehicle, AppointmentAppointmentsRecord::getCar);
            JavafxUtils.setColumnValue(payingCustomer, AppointmentAppointmentsRecord::getCustomer);
            JavafxUtils.setColumnValue(mechanic, AppointmentAppointmentsRecord::getMechanic);
            JavafxUtils.setColumnValue(date, AppointmentAppointmentsRecord::getDate);

            ObservableList<AppointmentAppointmentsRecord> list = FXCollections.observableArrayList();

            for (AppointmentAppointmentsRecord r : record) {
                list.add(r);
            }
            appointment.setItems(list);
        }
        
    }
    
    public List<AppointmentAppointmentsRecord> getAsAppointment(Result<AppointmentsRecord> appointment){
        List<AppointmentAppointmentsRecord> list = FXCollections.observableArrayList();
        
        CustomerCRUDService customerS = new CustomerCRUDService();
        CarCRUDService carS = new CarCRUDService();
        if(appointment != null && appointment.isNotEmpty()){
            for(AppointmentsRecord r : appointment){
                CarRecord car = carS.read(r.getCar());
                CustomerRecord cust = customerS.read(r.getCommissioner());

                AppointmentAppointmentsRecord app = new AppointmentAppointmentsRecord(r, cust, car);
                list.add(app);
            }
        }
        
        return list;
    } 
    
    public AppointmentAppointmentsRecord getSelected(){
        AppointmentAppointmentsRecord m = appointment.getSelectionModel().getSelectedItem();
        
        return m;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
