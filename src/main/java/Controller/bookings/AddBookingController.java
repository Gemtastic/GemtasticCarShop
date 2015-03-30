package Controller.bookings;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jooq.Result;
import services.CRUD.AppointmentCRUDService;
import services.CRUD.EmployeeCRUDService;
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.CarSearchService;
import services.MechanicSearchService;

/**
 *
 * @author Gemtastic
 */
public class AddBookingController implements Initializable{
    
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
    private Button createBookingBtn;
    
    @FXML
    private Button cancelBookingBtn;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private void create(){
        
        AppointmentCRUDService appointmentCRUD = new AppointmentCRUDService();
        MechanicSearchService mechanicSearch = new MechanicSearchService();
        CarSearchService carSearch = new CarSearchService();
        MakeCRUDService makeCRUD = new MakeCRUDService();
        ModelCRUDService modelCRUD = new ModelCRUDService();
        EmployeeCRUDService employeeCRUD = new EmployeeCRUDService();
        
        AppointmentsRecord r = new AppointmentsRecord();
        
        if(!customer.getText().isEmpty() && !type.getText().isEmpty() && !plates.getText().isEmpty()
            && scheduledDate.getValue() != null && mechanic.getSelectionModel().getSelectedItem() != null
            && !hour.getText().isEmpty() && !min.getText().isEmpty()){
            
            CarRecord car = carSearch.getByPlate(plates.getText());
            
            if(car != null){
                CarModelRecord model = modelCRUD.read(car.getCarModel());
                MakeRecord make = makeCRUD.read(model.getMake());
                Result<EmployeesRecord> makeMechanics = mechanicSearch.getAllMechanicsOf(make.getId());
                
                Object o = mechanic.getSelectionModel().getSelectedItem();
                int mechanicId = Integer.parseInt(o.toString());
                EmployeesRecord mech = employeeCRUD.read(mechanicId);
                
                boolean certified = makeMechanics.contains(mech);
                
                if(certified){
                    try{
                        int id = Integer.parseInt(customer.getText());
                        int h = Integer.parseInt(hour.getText());
                        int m = Integer.parseInt(min.getText());
                        
                        r.setCommissioner(id);
                        r.setCar(car.getId());
                        r.setComments(comments.getText());
                        r.setMechanic(mech.getId());
                        r.setType(type.getText());
                        
                        LocalTime time = LocalTime.of(h, m);
                        LocalDate date = scheduledDate.getValue();
                        LocalDateTime booking = LocalDateTime.of(date, time);
                        r.setScheduledDate(Timestamp.valueOf(booking));
                        
                        appointmentCRUD.create(r);
                        
                        errorMessage.setVisible(false);
                        errorMessage.setText("Något gick fel, kontrollera fälten!");

                        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, 
                                                            ApplicationNavigator.controller.bookingContent,
                                                            ApplicationNavigator.listBookingController);
                
                    }catch(NumberFormatException e){
                        errorMessage.setVisible(true);
                    }
                }else{
                    errorMessage.setVisible(true);
                }
            }else{
                errorMessage.setVisible(true);
                errorMessage.setText("Denna bil finns inte registrerad.");
            }
        }else{
            errorMessage.setVisible(true);
            errorMessage.setText("Var god fyll i alla fält.");
        }
    }
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, 
                                            ApplicationNavigator.controller.bookingContent,
                                            ApplicationNavigator.listBookingController);
    }
    
    private void setMechanics(){
        ObservableList list = FXCollections.observableArrayList();
        MechanicSearchService service = new MechanicSearchService();
        
        Result<EmployeesRecord> mechanics = service.getAllMechanics();
        
        if(mechanics != null){
            for(EmployeesRecord e : mechanics){
                list.add(e.getId().toString());
            }
            mechanic.setItems(list);
        }else{
            list.add("Det finns inga mekaniker");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMechanics();
        errorMessage.setVisible(false);
        errorMessage.setText("Något gick fel, kontrollera fälten!");
    }
    
}
