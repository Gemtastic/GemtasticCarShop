package Controller.malfunctions;

import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 *
 * @author Aizic Moisen
 */
public class ListMalfunctionController implements Initializable{

    @FXML
    private TableView<MalfunctionReportsRecord> bookings;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
