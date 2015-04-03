package Controller.malfunctions;

import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jooq.Result;
import util.JavafxUtils;

/**
 *
 * @author Aizic Moisen
 */
public class ListMalfunctionController implements Initializable{

    @FXML
    private TableView<MalfunctionReportsRecord> malfunction;
    
    @FXML
    private TableColumn<MalfunctionReportsRecord, Integer> malfunctionId;
    
    @FXML
    private TableColumn<MalfunctionReportsRecord, Date> date;
    
    @FXML
    private TableColumn<MalfunctionReportsRecord, Integer> malfunctioningCar;
    
    @FXML
    private TableColumn<MalfunctionReportsRecord, String> message;
    
    
    public void populateTable(Result<MalfunctionReportsRecord> record){
        if (record == null || record.isEmpty()) {
            ObservableList<MalfunctionReportsRecord> list = FXCollections.observableArrayList();
            malfunction.setItems(list);
        } else {
            JavafxUtils.setColumnValue(malfunctionId, MalfunctionReportsRecord::getId);
            JavafxUtils.setColumnValue(date, MalfunctionReportsRecord::getReportDate);
            JavafxUtils.setColumnValue(malfunctioningCar, MalfunctionReportsRecord::getCar);
            JavafxUtils.setColumnValue(message, MalfunctionReportsRecord::getMessage);

            ObservableList<MalfunctionReportsRecord> list = FXCollections.observableArrayList();

            for (MalfunctionReportsRecord r : record) {
                list.add(r);
            }
            malfunction.setItems(list);
        }
    }
    
    public void displayOne(MalfunctionReportsRecord r){
        if (r == null) {
            ObservableList<MalfunctionReportsRecord> list = FXCollections.observableArrayList();
            malfunction.setItems(list);
        } else {
            JavafxUtils.setColumnValue(malfunctionId, MalfunctionReportsRecord::getId);
            JavafxUtils.setColumnValue(date, MalfunctionReportsRecord::getReportDate);
            JavafxUtils.setColumnValue(malfunctioningCar, MalfunctionReportsRecord::getCar);
            JavafxUtils.setColumnValue(message, MalfunctionReportsRecord::getMessage);

            ObservableList<MalfunctionReportsRecord> list = FXCollections.observableArrayList();

                list.add(r);

            malfunction.setItems(list);
        }
    }
    
    public MalfunctionReportsRecord getSelected(){
        MalfunctionReportsRecord selected = malfunction.getSelectionModel().getSelectedItem();
        
        return selected;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
