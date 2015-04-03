package Controller.employees;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import services.MechanicSearchService;

/**
 *
 * @author Gemtastic
 */
public class DisplayEmployeeController implements Initializable {
    
    private EmployeesRecord employee;
    
    @FXML
    private Label username;
    
    @FXML
    private Label id;
    
    @FXML
    private Label phone;
    
    @FXML
    private Label email;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private ListView auctorisationList;
    
    public void loadEmployee(EmployeesRecord employee){
        this.employee = employee;
        username.setText(employee.getUsername());
        id.setText(String.valueOf(employee.getId()));
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail());
        
        getCertifications();
    }
    
    private void getCertifications(){
        
        MechanicSearchService service = new MechanicSearchService();
        
        ObservableList<String> makes = FXCollections.observableArrayList();
        List<MakeRecord> m = service.getAllMakesOf(this.employee.getId());
        
        auctorisationList.setItems(makes);
        
        if(m != null && !m.isEmpty()){
            for(MakeRecord r : m){
                makes.add(r.getMake());
            }
        }else{
            makes.add("Ingen behörighet finns.");
        }
        auctorisationList.setItems(makes);
    }
    
    @FXML
    private void editEmployee(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.editEmployees, 
                                            ApplicationNavigator.controller.employeeContent, 
                                            ApplicationNavigator.editEmployeeController);
        ApplicationNavigator.editEmployeeController.loadEmployee(employee);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
