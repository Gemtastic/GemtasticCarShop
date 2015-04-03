package Controller.malfunctions;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CRUD.CarCRUDService;
import services.CRUD.MalfunctionCRUDService;
import services.CarSearchService;

/**
 *
 * @author Gemtastic
 */
public class EditMalfunctionController implements Initializable{

    private MalfunctionReportsRecord editedReport;
    
    @FXML
    private TextField vehicle;
    
    @FXML
    private TextArea message;
    
    @FXML
    private DatePicker reportDate;
    
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
//            MalfunctionReportsRecord r = editedReport;
            
            CarRecord car = carS.getByPlate(vehicle.getText());
            
            if(car != null){
//                r.setCar(car.getId());
                editedReport.setCar(car.getId());
//                r.setMessage(message.getText());
                editedReport.setMessage(message.getText());
                if(reportDate.getValue() != null){
//                    r.setReportDate(Date.valueOf(reportDate.getValue()));
                    editedReport.setReportDate(Date.valueOf(reportDate.getValue()));
                }
                
                
                boolean success = service.update(editedReport);
                System.out.println(success);
            }else{
                errorMessage.setVisible(true);
            }
            errorMessage.setVisible(true);
        }
        
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, 
                                            ApplicationNavigator.controller.malfunctionsContent,
                                            ApplicationNavigator.listMalfunctionController);
    }
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, 
                                            ApplicationNavigator.controller.malfunctionsContent,
                                            ApplicationNavigator.listMalfunctionController);
    }
    
    public void loadReport(MalfunctionReportsRecord r){
        editedReport = r;
        CarCRUDService service = new CarCRUDService();
        CarRecord c = service.read(editedReport.getId());
        vehicle.setText(c.getLicensePlate());
        message.setText(editedReport.getMessage());
        reportDate.setValue(editedReport.getReportDate().toLocalDate());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
