package Controller.employees;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CertificationRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jooq.Result;
import services.CRUD.EmployeeCRUDService;
import services.LogInService;
import services.MakeSearchService;
import services.MechanicSearchService;

/**
 *
 * @author Gemtastic
 */
public class EditEmployeeController implements Initializable{
    
    private EmployeesRecord employee;
    
    private HashMap<String, MakeRecord> certifiedFor = new HashMap<>();
    private HashMap<String, MakeRecord> makesInDb = new HashMap<>();
    
    @FXML
    private Label errorMsg;
    
    @FXML
    private Label errorAdd;
    
    @FXML
    private Label missmatchError;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField phone;
    
    @FXML
    private TextField email;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private PasswordField repeatPassword;
    
    @FXML
    private ListView certificationList;
    
    @FXML
    private ListView makeList;
    
    @FXML
    private Button deleteMakeBtn;
    
    @FXML
    private Button addMakeBtn;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private void save(){
        missmatchError.setVisible(false);
        errorMsg.setVisible(false);
        
        if(password.getText().equals(repeatPassword.getText()) && !password.getText().isEmpty() && password != null){
            EmployeeCRUDService update = new EmployeeCRUDService();
            LogInService hashpw = new LogInService();
            String hashedpw = hashpw.hash(password.getText());
            employee.setEmail(email.getText());
            employee.setPhone(phone.getText());
            employee.setUsername(username.getText());
            employee.setPassword(hashedpw);

            boolean success = update.update(employee);

            if(success){
                ApplicationNavigator.loadTabContent(ApplicationNavigator.employee,
                                                    ApplicationNavigator.controller.employeeContent,
                                                    ApplicationNavigator.displayEmployeeController);
                ApplicationNavigator.displayEmployeeController.loadEmployee(this.employee);
            }else{
                errorMsg.setVisible(true);
            }
        }else{
            missmatchError.setVisible(true);
            password.setText(null);
            repeatPassword.setText(null);
        }
    }
    
    @FXML
    private void cancel(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.employee,
                                            ApplicationNavigator.controller.employeeContent,
                                            ApplicationNavigator.displayEmployeeController);
        ApplicationNavigator.displayEmployeeController.loadEmployee(employee);
    }
    
    @FXML
    private void deleteMake(){
        MechanicSearchService mechS = new MechanicSearchService();
        
        Object o = certificationList.getSelectionModel().getSelectedItem();
        String make = o.toString();
        
        MakeRecord m = certifiedFor.get(make);
        certifiedFor.remove(make);
        
        CertificationRecord c = mechS.getRecord(employee, m);
        mechS.delete(c);
        loadCertifiedMakes();
        loadMakes();
    }
    
    @FXML
    private void addMake(){
        MechanicSearchService mechS = new MechanicSearchService();
        
        Object o = makeList.getSelectionModel().getSelectedItem();
        if(o != null){
            String make = o.toString();

            MakeRecord m = makesInDb.get(make);
            CertificationRecord c = new CertificationRecord();
            c.setEmployee(employee.getId());
            c.setMake(m.getId());
            CertificationRecord success = mechS.create(c);
            
            if(success != null){
                errorAdd.setVisible(false);
                loadCertifiedMakes();
                loadMakes();
            }else{
                errorAdd.setVisible(true);
            }
        }
    }
    
    public void loadEmployee(EmployeesRecord employee){
        this.employee = employee;
        
        username.setText(employee.getUsername());
        email.setText(employee.getEmail());
        phone.setText(employee.getPhone());
        
        loadCertifiedMakes();
        loadMakes();
    }
    
    private void loadCertifiedMakes(){
        certifiedFor.clear();
        MechanicSearchService service = new MechanicSearchService();
        List<MakeRecord> records = service.getAllMakesOf(employee.getId());
        ObservableList<String> certifications = FXCollections.observableArrayList();
        
        if(records != null){
            for(MakeRecord m : records){
                String make = m.getMake();
                certifications.add(make);
                
                certifiedFor.put(make, m);
            }
            deleteMakeBtn.setDisable(false);
        }else{
            String make = "Inga certifikat finns.";
            certifications.add(make);
            deleteMakeBtn.setDisable(true);
        }
        
        certificationList.setItems(certifications);
    }
    
    private void loadMakes(){
        makesInDb.clear();
        MakeSearchService service = new MakeSearchService();
        Result<MakeRecord> makes = service.getAll();
        ObservableList<String> avalibleMakes = FXCollections.observableArrayList();
        
        if(makes.isNotEmpty()){
            for(MakeRecord m : makes){
                String make = m.getMake();
                makesInDb.put(make, m);
                if(!certifiedFor.containsKey(make)){
                    avalibleMakes.add(make);
                }
                
            }
            if(avalibleMakes.isEmpty()){
                avalibleMakes.add("Det finns inga märken");
                addMakeBtn.setDisable(true);
            }else{
                addMakeBtn.setDisable(false);
            }
            
        }else{
            avalibleMakes.add("Det finns inga märken");
            addMakeBtn.setDisable(true);
        }
        makeList.setItems(avalibleMakes);
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMsg.setVisible(false);
        missmatchError.setVisible(false);
        errorAdd.setVisible(false);
    }
    
}
