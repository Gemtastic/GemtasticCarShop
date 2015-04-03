package Controller.cars;

import AlternativeRecords.VehicleRecord;
import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
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
import services.CRUD.MakeCRUDService;
import services.CRUD.ModelCRUDService;
import util.JavafxUtils;

/**
 *
 * @author Aizic Moisen
 */
public class ListCarController implements Initializable{

    @FXML
    private TableView<VehicleRecord> vehicles;
    
    @FXML
    private TableColumn<VehicleRecord, Integer> carId;
    
    @FXML
    private TableColumn<VehicleRecord, String> plates;
    
    @FXML
    private TableColumn<VehicleRecord, Long> odometer;
    
    @FXML
    private TableColumn<VehicleRecord, String> make;
    
    @FXML
    private TableColumn<VehicleRecord, String> model;
    
    @FXML
    private TableColumn<VehicleRecord, Integer> year;
    
    public void populateTable(List<VehicleRecord> record){
        if (record == null || record.isEmpty()) {
            ObservableList<VehicleRecord> list = FXCollections.observableArrayList();
            vehicles.setItems(list);
        } else {
            JavafxUtils.setColumnValue(carId, VehicleRecord::getId);
            JavafxUtils.setColumnValue(plates, VehicleRecord::getPlates);
            JavafxUtils.setColumnValue(odometer, VehicleRecord::getOdometer);
            JavafxUtils.setColumnValue(make, VehicleRecord::getMake);
            JavafxUtils.setColumnValue(model, VehicleRecord::getModel);
            JavafxUtils.setColumnValue(year, VehicleRecord::getYear);
            

            ObservableList<VehicleRecord> list = FXCollections.observableArrayList();

            for (VehicleRecord r : record) {
                list.add(r);
            }
            vehicles.setItems(list);
        }
    }
    
    public CarRecord getSelected(){
        CarRecord car = null;
        
        CarCRUDService service = new CarCRUDService();
        
        VehicleRecord selected = vehicles.getSelectionModel().getSelectedItem();
        if(selected != null){
            car = service.read(selected.getId());
        }
        
        return car;
    }
    
    public List<VehicleRecord> getAsVehicleRecord(Result<CarRecord> car){
        List<VehicleRecord> list = FXCollections.observableArrayList();
        
        ModelCRUDService modelS = new ModelCRUDService();
        MakeCRUDService makeS = new MakeCRUDService();
        
        if(car != null && car.isNotEmpty()){
            for(CarRecord r : car){

                CarModelRecord mod = modelS.read(r.getCarModel());
                MakeRecord brand = makeS.read(mod.getMake());

                VehicleRecord vehicle = new VehicleRecord(r, mod, brand);
                list.add(vehicle);
            }
        }
        
        return list;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
