package Controller.cars;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import com.gemtastic.carshop.tables.records.OwnershipRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CRUD.CarCRUDService;
import services.CRUD.CustomerCRUDService;
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.CRUD.OwnershipService;

/**
 *
 * @author Gemtastic
 */
public class AddCarController implements Initializable {

    @FXML
    private Label errorMsg;
    
    @FXML
    private TextField make;
    
    @FXML
    private TextField model;
    
    @FXML
    private TextField year;
    
    @FXML
    private TextField plates;
    
    @FXML
    private TextField odometer;
    
    @FXML
    private TextField fuelType;
    
    @FXML
    private TextField owner;
    
    @FXML
    private Button addBtn;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listVehicles, 
                                            ApplicationNavigator.controller.vehicleContent,
                                            ApplicationNavigator.listCarController);
    }
    
    @FXML
    private void addVehicle(){
        errorMsg.setVisible(false);
        
        if(!make.getText().isEmpty() && !model.getText().isEmpty() && !year.getText().isEmpty() &&
           !plates.getText().isEmpty() && !odometer.getText().isEmpty() && !fuelType.getText().isEmpty()&&
            !owner.getText().isEmpty()){
            CarCRUDService carCRUD = new CarCRUDService();
            ModelCRUDService modelCRUD = new ModelCRUDService();
            MakeCRUDService makeCRUD = new MakeCRUDService();
            CustomerCRUDService cusCRUD = new CustomerCRUDService();
            OwnershipService ownService = new OwnershipService();
            
            
            CarRecord car = new CarRecord();
            CarModelRecord carModel = new CarModelRecord();
            MakeRecord makeRecord = new MakeRecord();
            OwnershipRecord ownership = new OwnershipRecord();
            CustomerRecord customer = null;
            
            makeRecord.setMake(make.getText());
            MakeRecord mr = makeCRUD.create(makeRecord);
            
            carModel.setFuelType(fuelType.getText());
            carModel.setMake(mr.getId());
            carModel.setModel(model.getText());
            
            try{
                int y = Integer.parseInt(year.getText());
                carModel.setModelYear(y);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
            
            CarModelRecord cmr = modelCRUD.create(carModel);
            
            car.setCarModel(cmr.getId());
            car.setLicensePlate(plates.getText());
            
            try{
                long odo = Integer.parseInt(odometer.getText());
                car.setOdometer(odo);
                customer = cusCRUD.read(Integer.parseInt(owner.getText()));
            }catch(NumberFormatException e){}
            
            CarRecord newCar = carCRUD.create(car);
            
            
            if(newCar.getId() != null && customer != null){
                
                ApplicationNavigator.loadTabContent(ApplicationNavigator.listVehicles, 
                                                    ApplicationNavigator.controller.vehicleContent,
                                                    ApplicationNavigator.listCarController);
                ownership.setCar(newCar.getId());
                ownership.setOwner(customer.getId());
                
                ownService.create(ownership);
                
                errorMsg.setVisible(false);
            }else{
                errorMsg.setVisible(true);
            }
        }else{
            errorMsg.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setVisible(false);
    }
    
}
