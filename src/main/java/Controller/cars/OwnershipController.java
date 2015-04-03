package Controller.cars;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.OwnershipRecord;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.CRUD.CustomerCRUDService;
import services.CRUD.OwnershipService;
import services.CarSearchService;

/**
 *
 * @author Gemtastic
 */
public class OwnershipController implements Initializable{
    
    private ObservableList<String> list = FXCollections.observableArrayList();
    private HashMap<String, CarRecord> cars = new HashMap<>();
    private HashMap<String, CustomerRecord> customers = new HashMap<>();
    
    private CustomerRecord customer;
    private CarRecord vehicle;

    @FXML
    private Label carOwner;
    
    @FXML
    private Label addRemove;
    
    @FXML
    private Label errorMsg;
    
    @FXML
    private TextField newByName;
    
    @FXML
    private ListView listOwnersVehicles;
    
    @FXML
    private Button addBtn;
    
    @FXML
    private Button removeBtn;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private void back(){
        System.out.println("The button was clicked!");
    }
    
    @FXML
    private void add(){
        errorMsg.setVisible(false);
        
        CarSearchService carS = new CarSearchService();
        CustomerCRUDService cusCRUD = new CustomerCRUDService();
        OwnershipService service = new OwnershipService();
        OwnershipRecord ownership = new OwnershipRecord();
        
        String text = newByName.getText();
        Integer id = null;
        
        try{
            id = Integer.parseInt(text);
        }catch(NumberFormatException e){}
        
        if(id != null){
            
            CustomerRecord c = cusCRUD.read(id);
            
            if(c != null){
                String preview = c.getId() + ", " + c.getFirstName() + " " + c.getLastName();
                customers.put(preview, c);
                list.add(preview);
                
                ownership.setCar(vehicle.getId());
                ownership.setOwner(c.getId());
                
                service.create(ownership);
                listOwnersVehicles.setItems(list);
            }else{
                errorMsg.setVisible(true);
            }
        } else if(text != null && id == null){
            CarRecord c = carS.getByPlate(text);
            
            if(c != null){
                String preview = c.getLicensePlate();
                cars.put(preview, c);
                list.add(preview);
                
                ownership.setCar(c.getId());
                ownership.setOwner(customer.getId());
                
                service.create(ownership);
                listOwnersVehicles.setItems(list);
            }else{
                errorMsg.setVisible(true);
            }
        }else{
            errorMsg.setVisible(true);
        }
        
    }
    
    @FXML
    private void remove(){
        
        OwnershipService service = new OwnershipService();
        OwnershipRecord ownership = new OwnershipRecord();
        
        Object selected = listOwnersVehicles.getSelectionModel().getSelectedItem();
        
        if(selected != null){
            String s = selected.toString();
            
            if(cars.containsKey(s)){
                ownership.setCar(cars.get(s).getId());
                ownership.setOwner(customer.getId());
                
                service.delete(ownership);
                
                cars.remove(s);
                list.remove(s);
                
            }else if(customers.containsKey(s)){
                ownership.setCar(vehicle.getId());
                ownership.setOwner(customers.get(s).getId());
                
                service.delete(ownership);
                
                customers.remove(s);
                list.remove(s);
            }
        }
    }
    
    public void setOwner(CustomerRecord customer){
        this.customer = customer;
        
        getCars();
        
        backBtn.setOnAction((ActionEvent e) -> {
            ApplicationNavigator.loadTabContent(ApplicationNavigator.customer,
                    ApplicationNavigator.controller.customerContent,
                    ApplicationNavigator.displayCustomersController);
//                ApplicationNavigator.displayCustomersController.loadCustomer(this.customer);
            ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.customerTab);
        });
        
        
        carOwner.setText(customer.getFirstName() + " " + customer.getLastName());
        addRemove.setText("Lägg till bil via nummerplåt");
        listOwnersVehicles.setItems(list);
    }
    
    public void setCar(CarRecord car){
        this.vehicle = car;
        
        getOwners();
        
        backBtn.setOnAction((ActionEvent e) -> {
            ApplicationNavigator.loadTabContent(ApplicationNavigator.vehicle, 
                                                ApplicationNavigator.controller.vehicleContent,
                                                ApplicationNavigator.displayCarController);
            ApplicationNavigator.displayCarController.loadCar(vehicle);
            ApplicationNavigator.setActiveTab(ApplicationNavigator.controller.vehicleTab);
        });
        
        carOwner.setText(customer.getFirstName() + " " + customer.getLastName());
        addRemove.setText("LÃ¤gg till Ã¤gare via kundnr");
        listOwnersVehicles.setItems(list);
    }
    
    public void getCars(){
        CarSearchService carS = new CarSearchService();
        
        List<CarRecord> ownedCars = carS.getAllCarsByOwner(customer.getId());
        
        list.clear();
        
        if(ownedCars != null && !ownedCars.isEmpty()){
            for(CarRecord r : ownedCars){
                cars.put(r.getLicensePlate(), r);
                list.add(r.getLicensePlate());
            }
            removeBtn.setDisable(false);
        }else{
            String msg = "Denna kund Ã¤ger inga bilar.";
            list.add(msg);
            removeBtn.setDisable(true);
        }
    }
    
    public void getOwners(){
        CarSearchService carS = new CarSearchService();
        
        List<CustomerRecord> ownedCars = carS.getOwnersByCar(vehicle.getId());
        
        list.clear();
        
        if(ownedCars != null && !ownedCars.isEmpty()){
            for(CustomerRecord r : ownedCars){
                String preview = r.getId() + ", " + r.getFirstName() + " " + r.getLastName();
                customers.put(preview, r);
                list.add(preview);
            }
            removeBtn.setDisable(false);
        }else{
            String msg = "Denna bil Ã¤r herrelÃ¶s.";
            list.add(msg);
            removeBtn.setDisable(true);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setVisible(false);
        list.clear();
    }
    
}
