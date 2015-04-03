package Controller.appointments;

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
import java.util.List;
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
import services.CRUD.AppointmentCRUDService;
import services.CRUD.CarCRUDService;
import services.CRUD.EmployeeCRUDService;
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.CarSearchService;
import services.MechanicSearchService;

/**
 *
 * @author Gemtastic
 */
public class EditAppointmentController implements Initializable{
    
    private AppointmentsRecord appointment;
    
    private boolean booking;
    
    @FXML
    private Label errorMessage;
    
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
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, 
                                            ApplicationNavigator.controller.bookingContent,
                                            ApplicationNavigator.listBookingController);
        ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.bookingTab);
        plates.setDisable(false);
        customer.setDisable(false);
    }
    
    @FXML
    private void edit(){
        AppointmentCRUDService appointmentCRUD = new AppointmentCRUDService();
        MechanicSearchService mechanicSearch = new MechanicSearchService();
        CarSearchService carSearch = new CarSearchService();
        MakeCRUDService makeCRUD = new MakeCRUDService();
        ModelCRUDService modelCRUD = new ModelCRUDService();
        EmployeeCRUDService employeeCRUD = new EmployeeCRUDService();
        
        AppointmentsRecord r = new AppointmentsRecord();
        
        if(!customer.getText().isEmpty() && !type.getText().isEmpty() && !plates.getText().isEmpty()
            && scheduledDate.getValue() != null && !mechanic.isDisabled()
            && !hour.getText().isEmpty() && !min.getText().isEmpty()){
            
            CarRecord car = carSearch.getByPlate(plates.getText());
            
            Object o = mechanic.getSelectionModel().getSelectedItem();
            
            if(car != null && o != null){
                CarModelRecord model = modelCRUD.read(car.getCarModel());
                MakeRecord make = makeCRUD.read(model.getMake());
                List<EmployeesRecord> makeMechanics = mechanicSearch.getAllMechanicsOf(make.getId());
                
                
                int mechanicId = Integer.parseInt(o.toString());
                
                EmployeesRecord mech = employeeCRUD.read(mechanicId);
                
                if(makeMechanics != null && !makeMechanics.isEmpty()){
                    boolean certified = makeMechanics.contains(mech);

                    if(certified){
                        try{
                            int id = Integer.parseInt(customer.getText());
                            int h = Integer.parseInt(hour.getText());
                            int m = Integer.parseInt(min.getText());

                            r.setId(appointment.getId());
                            r.setCommissioner(id);
                            r.setCar(car.getId());
                            r.setComments(comments.getText());
                            r.setMechanic(mech.getId());
                            r.setType(type.getText());

                            LocalTime time = LocalTime.of(h, m);
                            LocalDate date = scheduledDate.getValue();
                            LocalDateTime b = LocalDateTime.of(date, time);
                            
                            if(booking == true){
                                r.setScheduledDate(Timestamp.valueOf(b));
                            }else{
                                r.setPerformedDate(Timestamp.valueOf(b));
                            }
                            
                            boolean success = appointmentCRUD.update(r);
                            
                            if(success){
                                errorMessage.setVisible(false);
                                errorMessage.setText("Något gick fel, kontrollera fälten!");

                            ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, 
                                                                ApplicationNavigator.controller.bookingContent,
                                                                ApplicationNavigator.listBookingController);
                            plates.setDisable(false);
                            customer.setDisable(false);
                            }else{
                                errorMessage.setVisible(true);
                                errorMessage.setText("Något gick fel, kontrollera fälten!");
                                plates.setDisable(true);
                                customer.setDisable(true);
                            }
                            

                        }catch(NumberFormatException e){
                            errorMessage.setVisible(true);
                            plates.setDisable(true);
                            customer.setDisable(true);
                        }
                    }else{
                        errorMessage.setVisible(true);
                        plates.setDisable(true);
                        customer.setDisable(true);
                    }
                }else{
                    errorMessage.setVisible(true);
                    errorMessage.setText("Denna mekaniker är inte behörig!");
                    plates.setDisable(true);
                    customer.setDisable(true);
                }
            }else{
                errorMessage.setVisible(true);
                errorMessage.setText("Var god fyll i alla fält.");
                plates.setDisable(true);
                customer.setDisable(true);
            }
        }else{
            errorMessage.setVisible(true);
            errorMessage.setText("Var god fyll i alla fält.");
            plates.setDisable(true);
            customer.setDisable(true);
        }
        plates.setDisable(false);
        customer.setDisable(false);
    }
    
    public void loadAppointment(AppointmentsRecord record){
        CarCRUDService service = new CarCRUDService();
        this.appointment = record;
        
        booking = false;
        
        customer.setText(String.valueOf(appointment.getCommissioner()));
        customer.setDisable(true);
        plates.setText(service.read(appointment.getCar()).getLicensePlate());
        plates.setDisable(true);
        type.setText(appointment.getType());
        comments.setText(appointment.getComments());
        
        LocalDateTime date = appointment.getPerformedDate().toLocalDateTime();
        hour.setText(String.valueOf(date.getHour()));
        min.setText(String.valueOf(date.getMinute()));
        scheduledDate.setValue(date.toLocalDate());
        
        setMechanics();
    }
    
    public void loadBooking(AppointmentsRecord record){
        CarCRUDService service = new CarCRUDService();
        this.appointment = record;
        
        booking = true;
        
        customer.setText(String.valueOf(appointment.getCommissioner()));
        customer.setDisable(true);
        plates.setText(service.read(appointment.getCar()).getLicensePlate());
        plates.setDisable(true);
        type.setText(appointment.getType());
        comments.setText(appointment.getComments());
        
        LocalDateTime date = appointment.getScheduledDate().toLocalDateTime();
        hour.setText(String.valueOf(date.getHour()));
        min.setText(String.valueOf(date.getMinute()));
        scheduledDate.setValue(date.toLocalDate());
        
        setMechanics();
    }
    
    private void setMechanics(){
        ObservableList<String> list = FXCollections.observableArrayList();
        MechanicSearchService service = new MechanicSearchService();
        
        List<EmployeesRecord> mechanics = service.getAllMechanics();
        
        mechanic.setItems(list);
        
        if(mechanics != null){
            for(EmployeesRecord e : mechanics){
                if(!list.contains(e.getId().toString())){
                    list.add(e.getId().toString());
                }
            }
            mechanic.setItems(list);
        }else{
            list.add("Det finns inga mekaniker");
            mechanic.setItems(list);
            mechanic.setValue(list.get(0));
            mechanic.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plates.setDisable(false);
        customer.setDisable(false);
        setMechanics();
        errorMessage.setVisible(false);
        errorMessage.setText("Något gick fel, kontrollera fälten!");
    }
    
}
