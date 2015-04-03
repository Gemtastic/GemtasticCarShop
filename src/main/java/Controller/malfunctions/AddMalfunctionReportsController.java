package Controller.malfunctions;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CRUD.MalfunctionCRUDService;
import services.CarSearchService;

/**
 *
 * @author Gemtastic
 */
public class AddMalfunctionReportsController implements Initializable{

    private CarRecord displayedVehicle;
    
    @FXML
    private TextField vehicle;
    
    @FXML
    private TextArea message;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private void save(){
        errorMessage.setVisible(false);
        
        if(!vehicle.getText().isEmpty() && !message.getText().isEmpty()){
            MalfunctionCRUDService service = new MalfunctionCRUDService();
            CarSearchService carS = new CarSearchService();
            MalfunctionReportsRecord r = new MalfunctionReportsRecord();
            
            CarRecord car = carS.getByPlate(vehicle.getText());
            
            if(car != null){
                r.setCar(car.getId());
                r.setMessage(message.getText());
                
                service.create(r);
                
                ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, 
                                                    ApplicationNavigator.controller.malfunctionsContent,
                                                    ApplicationNavigator.listMalfunctionController);
                ApplicationNavigator.loadTabContent(ApplicationNavigator.vehicle, 
                                                    ApplicationNavigator.controller.vehicleContent,
                                                    ApplicationNavigator.displayCarController);
                ApplicationNavigator.displayCarController.loadCar(this.displayedVehicle);
            }else{
                errorMessage.setVisible(true);
            }
            errorMessage.setVisible(true);
        }
    }
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, 
                                            ApplicationNavigator.controller.malfunctionsContent,
                                            ApplicationNavigator.listMalfunctionController);
    }
    
    public void setVehicle(CarRecord car){
        this.displayedVehicle = car;
        
        vehicle.setText(car.getLicensePlate());
        vehicle.setDisable(true);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicle.setDisable(false);
    }
    
}
