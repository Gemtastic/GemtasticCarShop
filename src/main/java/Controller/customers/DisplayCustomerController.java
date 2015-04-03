package Controller.customers;

import services.AppointmentSearchService;
import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AddressRecord;
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
import services.CRUD.AddressCRUDService;
import services.CRUD.CarCRUDService;
import services.CarSearchService;
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import services.MalfunctionSearchService;

/**
 *
 * @author Gemtastic
 */
public class DisplayCustomerController implements Initializable {

    private CustomerRecord customer;
    private Map<String, CarRecord> cars = new HashMap<>();
    private Map<String, MalfunctionReportsRecord> malfunctions = new HashMap<>();

    @FXML
    private Label name;

    @FXML
    private Label age;

    @FXML
    private Label gender;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    @FXML
    private Label customerId;

    @FXML
    private Label street;

    @FXML
    private Label co;

    @FXML
    private Label city;

    @FXML
    private Label zip;
    
    @FXML
    private Label appointmentCount;

    @FXML
    private ListView vehicles;
    
    @FXML
    private Button addBooking;
    
    @FXML
    private Button addVehicleBtn;

    @FXML
    private Button showCar;
    

    public void cleanSlate() {
        this.name.setVisible(false);
        this.age.setVisible(false);
        this.gender.setVisible(false);
        this.phone.setVisible(false);
        this.email.setVisible(false);
        this.customerId.setVisible(false);
        this.street.setVisible(false);
        this.co.setVisible(false);
        this.city.setVisible(false);
        this.zip.setVisible(false);
    }

    public void loadCustomer(CustomerRecord customer) {
        this.customer = customer;

        AddressCRUDService addressService = new AddressCRUDService();
        AddressRecord address = addressService.read(customer.getAddress());
        AppointmentSearchService appointmentService = new AppointmentSearchService();

        this.name.setText(customer.getFirstName() + " " + customer.getLastName());
        this.phone.setText(customer.getPhone());
        this.customerId.setText(String.valueOf(customer.getId()));
        this.email.setText(customer.getEmail());
        this.street.setText(address.getStreet());
        if (address.getCo() != null) {
            this.co.setText(address.getCo());
            this.co.setVisible(true);
        }
        this.city.setText(address.getCity());
        this.zip.setText(String.valueOf(address.getZip()));

        // Todo add age
        String g = customer.getGender().toLowerCase();

        switch (g) {
            case "f":
                this.gender.setText("Kvinna");
                break;
            case "m":
                this.gender.setText("Man");
                break;
            default:
                this.gender.setText("Hen");
                break;
        }

        this.name.setVisible(true);
//        this.age.setVisible(true);
        this.gender.setVisible(true);
        this.phone.setVisible(true);
        this.email.setVisible(true);
        this.customerId.setVisible(true);
        this.street.setVisible(true);
        this.city.setVisible(true);
        this.zip.setVisible(true);

        getVehicles();

    }

    private void getVehicles() {
        ObservableList<String> carInfo = FXCollections.observableArrayList();

        CarSearchService carService = new CarSearchService();
        MakeCRUDService makeService = new MakeCRUDService();
        ModelCRUDService modelService = new ModelCRUDService();

        List<CarRecord> record = carService.getAllCarsByOwner(customer.getId());

        if (!record.isEmpty()) {
            for (CarRecord r : record) {
                CarModelRecord model = modelService.read(r.getCarModel());
                MakeRecord make = makeService.read(model.getMake());

                String plates = r.getLicensePlate();
                String makeName = make.getMake();
                String modelName = model.getModel();

                String preview = plates + ", " + makeName + ", " + modelName + ".";

                carInfo.add(preview);
                this.cars.put(preview, r);
            }
            showCar.setDisable(false);
        } else {
            String message = "Denna kund äger inga bilar.";
            carInfo.add(message);
            showCar.setDisable(true);
            
        }
        this.vehicles.setItems(carInfo);
    }
    
    @FXML
    private void addVehicle(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.ownership,
                                            ApplicationNavigator.controller.customerContent,
                                            ApplicationNavigator.editOwnershipController);
        ApplicationNavigator.editOwnershipController.setOwner(this.customer);
        
    }

    @FXML
    private void displayCar() {

        CarRecord r = null;
        Object selected = vehicles.getSelectionModel().getSelectedItem();
        
        if(selected != null){
            String info = selected.toString();

            for (String s : cars.keySet()) {
                if (s.equals(info)) {
                    r = cars.get(s);
                    break;
                }
            }
            ApplicationNavigator.loadTabContent(ApplicationNavigator.vehicle,
                                                ApplicationNavigator.controller.vehicleContent,
                                                ApplicationNavigator.displayCarController);
            ApplicationNavigator.displayCarController.loadCar(r);

            ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.vehicleTab);
        }
        
    }
    
    @FXML
    private void editCustomer() {
        ApplicationNavigator.loadTabContent(ApplicationNavigator.editCustomers,
                ApplicationNavigator.controller.customerContent,
                ApplicationNavigator.editCustomersController);
        ApplicationNavigator.editCustomersController.loadCustomer(customer);

    }
    
    @FXML
    private void newCustomerBooking(){
        
        
        CarRecord r = null;
        Object selected = vehicles.getSelectionModel().getSelectedItem();
        
        if(selected != null){
            try{
                String info = selected.toString();

                for (String s : cars.keySet()) {
                    if (s.equals(info)) {
                        r = cars.get(s);
                        break;
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addBookings,
                                                ApplicationNavigator.controller.bookingContent,
                                                ApplicationNavigator.addBookingController);
            ApplicationNavigator.addBookingController.loadBooking(r, this.customer);
            ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.bookingTab);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cleanSlate();
    }

}
