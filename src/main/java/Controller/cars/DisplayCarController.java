package Controller.cars;

import Controller.navigators.ApplicationNavigator;
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
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.CarSearchService;

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

    public void loadCar(CarRecord r) {

        ModelCRUDService modelCRUD = new ModelCRUDService();
        MakeCRUDService makeCRUD = new MakeCRUDService();

        this.displayedCar = r;
        displayedModel = modelCRUD.read(r.getCarModel());
        displayedMake = makeCRUD.read(displayedModel.getMake());

        plates.setText(r.getLicensePlate());
        odometer.setText(String.valueOf(r.getOdometer()));
        fuelType.setText(displayedModel.getFuelType());
        make.setText(displayedMake.getMake());
        model.setText(displayedModel.getModel());
        year.setText(String.valueOf(displayedModel.getModelYear()));

        getOwner.setDisable(false);

        getOwners();
    }

    private void getMalfunctions() {

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
    private void displayOwner() {

        CustomerRecord r = null;
        Object selected = ownerList.getSelectionModel().getSelectedItem();
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
        }
    }
    
    @FXML
    private void displayMalfunction(){
        
    }
    
    @FXML
    private void displayAppointments(){
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
