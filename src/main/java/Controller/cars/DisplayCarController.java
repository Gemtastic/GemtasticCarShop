package Controller.cars;

import AlternativeRecords.AppointmentAppointmentsRecord;
import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.jooq.Result;
import services.AppointmentSearchService;
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.CarSearchService;
import services.MalfunctionSearchService;

/**
 *
 * @author Aizic Moisen
 */
public class DisplayCarController implements Initializable {

    private CarRecord displayedCar;
    private CarModelRecord displayedModel;
    private MakeRecord displayedMake;

    private Map<String, CustomerRecord> ownerMap = new HashMap<>();
    private Map<String, MalfunctionReportsRecord> malfunctions = new HashMap<>();

    @FXML
    private Label plates;

    @FXML
    private Label odometer;

    @FXML
    private Label fuelType;

    @FXML
    private Label make;

    @FXML
    private Label model;

    @FXML
    private Label year;

    @FXML
    private ListView ownerList;

    @FXML
    private ListView malfunctionsList;

    @FXML
    private Button getOwner;
    
    @FXML
    private Button showAppointments;
    
    @FXML
    private Button showMalfunction;
    
    @FXML
    private Button addOwnerBtn;
    
    @FXML
    private Button newMalfunctionBtn;

    
    public void loadCar(CarRecord r) {
        
        ModelCRUDService modelCRUD = new ModelCRUDService();
        MakeCRUDService makeCRUD = new MakeCRUDService();

        this.displayedCar = r;
        displayedModel = modelCRUD.read(displayedCar.getCarModel());
        displayedMake = makeCRUD.read(displayedModel.getMake());
        
        this.plates.setText(displayedCar.getLicensePlate());
        this.odometer.setText(String.valueOf(displayedCar.getOdometer()));
        this.fuelType.setText(displayedModel.getFuelType());
        this.make.setText(displayedMake.getMake());
        this.model.setText(displayedModel.getModel());
        this.year.setText(String.valueOf(displayedModel.getModelYear()));

        getOwner.setDisable(false);

        getOwners();
        getMalfunctions();
    }

    private void getMalfunctions() {

        MalfunctionSearchService malfS = new MalfunctionSearchService();
        
        ObservableList<String> malfInfo = FXCollections.observableArrayList();
        Result<MalfunctionReportsRecord> results = malfS.getAllWhere("car", String.valueOf(displayedCar.getId()));
        
        if(results.isNotEmpty()){
            for(MalfunctionReportsRecord r : results){
                String preview = r.getId() + ", " + r.getReportDate().toString();
                malfunctions.put(preview, r);
                
                malfInfo.add(preview);
            }
            showMalfunction.setDisable(false);
        }else{
            String message = "Denna bil har ingen felanmälan.";
            malfInfo.add(message);
            showMalfunction.setDisable(true);
        }
        this.malfunctionsList.setItems(malfInfo);
    }

    private void getOwners() {
        CarSearchService carService = new CarSearchService();

        ObservableList<String> ownerInfo = FXCollections.observableArrayList();
        List<CustomerRecord> owners = carService.getOwnersByCar(displayedCar.getId());

        if (!owners.isEmpty()) {
            for (CustomerRecord r : owners) {

                String firstName = r.getFirstName();
                String lastName = r.getLastName();
                String customerId = String.valueOf(r.getId());

                String preview = firstName + ", " + lastName + ", " + customerId + ".";

                ownerInfo.add(preview);
                this.ownerMap.put(preview, r);
            }
            getOwner.setDisable(false);
        } else {
            String message = "Något är fel; den här bilen är herrelös.";
            ownerInfo.add(message);
            getOwner.setDisable(true);
        }
        this.ownerList.setItems(ownerInfo);
    }
    
    @FXML
    private void newMalfunction(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addMalfunctions, 
                                            ApplicationNavigator.controller.malfunctionsContent,
                                            ApplicationNavigator.addMalfunctionController);
        ApplicationNavigator.addMalfunctionController.setVehicle(displayedCar);
        ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.malfunctionTab);
    }
    
    @FXML
    private void addOwner(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.ownership,
                                            ApplicationNavigator.controller.vehicleContent,
                                            ApplicationNavigator.editOwnershipController);
        ApplicationNavigator.editOwnershipController.setCar(this.displayedCar);
        ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.vehicleTab);
    }

    @FXML
    private void displayOwner() {

        CustomerRecord r = null;
        Object selected = ownerList.getSelectionModel().getSelectedItem();
        

        if(selected != null){
            String info = selected.toString();
            
            for (String s : ownerMap.keySet()) {
                if (s.equals(info)) {
                    r = ownerMap.get(s);
                    break;
                }
            }
            if (r != null) {
            ApplicationNavigator.loadTabContent(ApplicationNavigator.customer,
                                                ApplicationNavigator.controller.customerContent,
                                                ApplicationNavigator.displayCustomersController);
            ApplicationNavigator.displayCustomersController.loadCustomer(r);
            ApplicationNavigator.controller.cleanSlate();
            ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.customerTab);
            }
        }
    }
    
    @FXML
    private void displayMalfunction(){
        
        MalfunctionReportsRecord r = null;
        Object selected = malfunctionsList.getSelectionModel().getSelectedItem();
        
        if (selected != null){
            String info = selected.toString();
            for(String s : malfunctions.keySet()){
                if(s.equals(info)){
                    r = malfunctions.get(info);
                    break;
                }
            }
        
            if(r != null){
                ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions,
                                                    ApplicationNavigator.controller.malfunctionsContent,
                                                    ApplicationNavigator.listMalfunctionController);
                ApplicationNavigator.listMalfunctionController.displayOne(r);
                ApplicationNavigator.controller.cleanSlate();
                ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.malfunctionTab);
            }
        }
    }
    
    @FXML
    private void displayAppointments(){
        AppointmentSearchService service = new AppointmentSearchService();
        Result<AppointmentsRecord> records = service.getAllWhere("carid", String.valueOf(displayedCar.getId()));
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listAppointments,
                                                ApplicationNavigator.controller.appointmentContent,
                                                ApplicationNavigator.listAppointmentController);
        List<AppointmentAppointmentsRecord> app = ApplicationNavigator.listAppointmentController.getAsAppointment(records);
        ApplicationNavigator.listAppointmentController.populateTable(app);
        ApplicationNavigator.controller.cleanSlate();
        ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.appointmentTab);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
