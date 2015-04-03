package Controller.statistics;

import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.jooq.Result;
import services.AppointmentSearchService;
import services.CustomerSearchService;
import services.EmployeeSearchService;
import services.MakeSearchService;
import services.MalfunctionSearchService;
import services.MechanicSearchService;
import services.StatisticsService;

/**
 *
 * @author Gemtastic
 */
public class StatisticsController implements Initializable{
    
    @FXML
    private Label employeeCount;
    
    @FXML
    private Label mechanicCount;
    
    @FXML
    private Label makeCount;
    
    @FXML
    private Label youngestCustomer;
    
    @FXML
    private Label oldestCustomer;
    
    @FXML
    private Label appLastYear;
    
    @FXML
    private Label averageAppThisYear;
    
    @FXML
    private Label averageAppLastYear;
    
    @FXML
    private Label averageAppThisMonth;
    
    @FXML
    private Label customerCount;
    
    @FXML
    private Label maleCount;
    
    @FXML
    private Label femaleCount;
    
    @FXML
    private Label femaleAppCount;
    
    @FXML
    private Label maleAppCount;
    
    @FXML
    private Label bookingsCount;
    
    @FXML
    private Label appCount;
    
    @FXML
    private Label malfunctionCount;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomerSearchService cusS = new CustomerSearchService();
        EmployeeSearchService empS = new EmployeeSearchService();
        AppointmentSearchService appS = new AppointmentSearchService();
        MalfunctionSearchService malfS = new MalfunctionSearchService();
        MakeSearchService makeS = new MakeSearchService();
        MechanicSearchService mechS = new MechanicSearchService();
        StatisticsService service = new StatisticsService();
        
        Result<CustomerRecord> customers = cusS.getAll();
        Result<EmployeesRecord> employees = empS.getAll();
        List<EmployeesRecord> mechanics = mechS.getAllMechanics();
        Result<MakeRecord> makes = makeS.getAll();
        Result<MalfunctionReportsRecord> malfunctions = malfS.getAll();
        Result<AppointmentsRecord> appointments = appS.getAll();
        Result<AppointmentsRecord> bookings = appS.getAllWhere("booking", "0");
        
        customerCount.setText(String.valueOf(customers.size()));
        employeeCount.setText(String.valueOf(employees.size()));
        mechanicCount.setText(String.valueOf(mechanics.size()));
        makeCount.setText(String.valueOf(makes.size()));
        appCount.setText(String.valueOf(appointments.size()));
        appLastYear.setText(String.valueOf(service.getAppointmentsLastYear()));
        averageAppLastYear.setText(String.valueOf(service.getAverageAppLastYear()));
        averageAppThisMonth.setText(String.valueOf(service.getAverageAppThisMonth()));
        averageAppThisYear.setText(String.valueOf(service.getAverageAppThisYear()));
        bookingsCount.setText(String.valueOf(bookings.size()));
        femaleAppCount.setText(String.valueOf(service.getAmountByGender('F')));
        femaleCount.setText(String.valueOf(service.getAppointmentsByGender('F')));
        maleAppCount.setText(String.valueOf(service.getAmountByGender('M')));
        maleCount.setText(String.valueOf(service.getAppointmentsByGender('M')));
        malfunctionCount.setText(String.valueOf(malfunctions.size()));
        oldestCustomer.setText(String.valueOf(service.getAge("o")) + " år.");
        youngestCustomer.setText(String.valueOf(service.getAge("y")) + " år."); 
    }
    
}
