package Controller.appointments;

import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Gemtastic
 */
public class EditAppointmentController implements Initializable{
    
    private AppointmentsRecord appointment;
    
    @FXML
    private TextField customer;
    
    @FXML
    private TextField type;
    
    @FXML
    private TextField plates;
    
    @FXML
    private TextField hour;
    
    @FXML
    private TextField min;
    
    @FXML
    private TextArea comments;
    
    @FXML
    private DatePicker scheduledDate;
    
    @FXML
    private ChoiceBox mechanic;
    
    @FXML
    private Button editBookingBtn;
    
    @FXML
    private Button cancelBookingBtn;
    
    @FXML
    private void cancel(){
        
    }
    
    @FXML
    private void edit(){
        
    }
    
    public void loadAppointment(AppointmentsRecord record){
        this.appointment = record;
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
