package Controller;

import Controller.appointments.AppointmentSearchService;
import Controller.customers.ListCustomerController;
import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jooq.Result;
import services.CRUD.AddressCRUDService;
import services.CRUD.CustomerCRUDService;
import services.CarSearchService;
import services.CustomerSearchService;
import services.EmployeeSearchService;

/**
 * FXML Controller class
 *
 * @author Gemtastic
 */
public class ApplicationController implements Initializable {

    ListCustomerController customersView;

    @FXML
    public TabPane tabPane;
    
    @FXML
    public Tab customerTab;
    @FXML
    public Tab vehicleTab;
    @FXML
    public Tab bookingTab;
    @FXML
    public Tab appointmentTab;
    @FXML
    public Tab employeeTab;
    @FXML
    public Tab malfunctionTab;
    @FXML
    public Tab statisticTab;
    
    // Customer tab methods and data
    @FXML
    public BorderPane customerContent;

    @FXML
    private Button customerSearch;

    @FXML
    private TextField customerSearchField;

    @FXML
    private ChoiceBox customerCb;

    @FXML
    private Label errorSearchCustomer;

    @FXML
    private Button showCustomerbtn;
    
    @FXML
    private Button removeCustomerBtn;
    
    @FXML
    private Button addCustomerBtn;
    
    
    // Vehicle tab, methods and data
    @FXML
    public BorderPane vehicleContent;
    
    @FXML
    private Button vehicleSearchBtn;
    
    @FXML
    private Button newVehicleBtn;
    
    @FXML
    private Button deleteVehicleBtn;
    
    @FXML
    private Button showCarBtn;
    
    @FXML
    private TextField vehicleSearchField;
    
    @FXML
    private ChoiceBox vehicleCb;
    
    @FXML
    private Label errorSearchVehicle;
    
    
    // Appointment tab, methods and data
    @FXML
    public BorderPane appointmentContent;
    
    @FXML
    private Button appointmentSearchBtn;
    
    @FXML
    private Button newAppointmentBtn;
    
    @FXML
    private Button deleteAppointmentBtn;
    
    @FXML
    private Button showAppointmentBtn;
    
    @FXML
    private TextField appointmentSearchField;
    
    @FXML
    private ChoiceBox appointmentCb;
    
    @FXML
    private Label errorSearchAppointment;
    
    
    // Bookings tab, methods and data
    @FXML
    public BorderPane bookingContent;
    
    @FXML
    private Button bookingSearchBtn;
    
    @FXML
    private Button newBookingBtn;
    
    @FXML
    private Button deleteBookingBtn;
    
    @FXML
    private Button showBookingBtn;
    
    @FXML
    private TextField bookingSearchField;
    
    @FXML
    private ChoiceBox bookingCb;
    
    @FXML
    private Label errorSearchBooking;
    
    
    // Employee tab, methods and data
    @FXML
    public BorderPane employeeContent;
    
    @FXML
    private Button employeeSearchBtn;
    
    @FXML
    private Button newEmployeeBtn;
    
    @FXML
    private Button deleteEmployeeBtn;
    
    @FXML
    private Button showEmployeeBtn;
    
    @FXML
    private TextField employeeSearchField;
    
    @FXML
    private ChoiceBox employeeCb;
    
    @FXML
    private Label errorSearchEmployee;
    
    
    // Malfunction tab, methods and data
    @FXML
    public BorderPane malfunctionsContent;
    
    @FXML
    private Button malfunctionSearchBtn;
    
    @FXML
    private Button newMalfunctionBtn;
    
    @FXML
    private Button deleteMalfunctionBtn;
    
    @FXML
    private Button showMalfunctionBtn;
    
    @FXML
    private TextField malfunctionSearchField;
    
    @FXML
    private ChoiceBox malfunctionCb;
    
    @FXML
    private Label errorSearchMalfunction;
    
    
    // Statistics tab, methods and data

    @FXML
    private void searchCustomer() throws IOException {
        showCustomerbtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, customerContent,
                ApplicationNavigator.listCustomersController);

        CustomerSearchService service = new CustomerSearchService();
        Result<CustomerRecord> customers = null;
        String search = customerSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = customerCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (customerSearchField.getText().isEmpty() && column == null) {
            customers = service.getAll();
            errorSearchCustomer.setVisible(false);
            ApplicationNavigator.listCustomersController.populateTable(customers);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.listCustomersController.populateTable(customers);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.listCustomersController.populateTable(customers);
        } else {
            customers = service.getAllWhere(column, search);

            if (customers == null) {
                errorSearchCustomer.setVisible(true);
            } else {
                errorSearchCustomer.setVisible(false);

            }
            ApplicationNavigator.listCustomersController.populateTable(customers);
        }
        
        customerCb.setValue(null);
    }

    public void showCustomer() {
        CustomerRecord customer = ApplicationNavigator.getSelectedCustomer();
        if (customer != null) {
            ApplicationNavigator.loadTabContent(ApplicationNavigator.customer, customerContent, 
                                                ApplicationNavigator.displayCustomersController);
            ApplicationNavigator.displayCustomersController.loadCustomer(customer);

            showCustomerbtn.setDisable(true);
        }
    }
    
    @FXML
    private void removeCustomer(){
        CustomerCRUDService customerService = new CustomerCRUDService();
        AddressCRUDService addressService = new AddressCRUDService();
        CustomerRecord customer = ApplicationNavigator.getSelectedCustomer();
        if (customer != null) {
            customerService.delete(customer.getId());
            addressService.delete(customer.getAddress());
            
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, customerContent, 
                                                ApplicationNavigator.listCustomersController);
        }
    }
    
    @FXML
    private void addCustomer(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addCustomers, customerContent, 
                                                ApplicationNavigator.addCustomersController);
    }
    
    
    // Booking methods
    @FXML
    private void searchBookings(){
        showBookingBtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, bookingContent,
                ApplicationNavigator.listBookingController);

        AppointmentSearchService service = new AppointmentSearchService();
        CarSearchService carService = new CarSearchService();
        
        Result<AppointmentsRecord> appointments = null;
        String search = bookingSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = bookingCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (appointmentSearchField.getText().isEmpty() && column == null) {
            appointments = service.getAll();
            errorSearchAppointment.setVisible(false);
            ApplicationNavigator.listAppointmentController.populateTable(appointments);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.listAppointmentController.populateTable(appointments);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.listAppointmentController.populateTable(appointments);
        } else {
            
            CarRecord car = carService.getByPlate(search);
            String id = String.valueOf(car.getId());
            appointments = service.getAllWhere(column, id);

            if (appointments == null) {
                errorSearchCustomer.setVisible(true);
            } else {
                errorSearchCustomer.setVisible(false);

            }
            
            Result<AppointmentsRecord> bookings = service.getAllWhere("booking", column);
            
            ApplicationNavigator.listAppointmentController.populateTable(bookings);
        }
        
        bookingCb.setValue(null);
    }
    
    @FXML
    private void displayBooking(){
        
    }
    @FXML
    private void deleteBooking(){
        
    }
    
    // Appointments and Bookings are the same in the DB
    @FXML
    private void newAppointment(){
        
    }
    
    // Appointment methods
    @FXML
    private void searchAppointment(){
        
    }
    
    @FXML
    private void displayAppointment(){
        
    }
    
    @FXML
    private void deleteAppointment(){
        
    }
    
    
    // Employee methods
    @FXML
    private void searchEmployee(){
        showEmployeeBtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listEmployees, employeeContent,
                ApplicationNavigator.listEmployeesController);

        EmployeeSearchService service = new EmployeeSearchService();
        Result<EmployeesRecord> employees = null;
        String search = employeeSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = employeeCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (employeeSearchField.getText().isEmpty() && column == null) {
            employees = service.getAll();
            errorSearchEmployee.setVisible(false);
            ApplicationNavigator.listEmployeesController.populateTable(employees);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchEmployee.setVisible(true);
            ApplicationNavigator.listEmployeesController.populateTable(employees);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchEmployee.setVisible(true);
            ApplicationNavigator.listEmployeesController.populateTable(employees);
        } else {
            employees = service.getAllWhere(column, search);

            if (employees == null) {
                errorSearchEmployee.setVisible(true);
            } else {
                errorSearchEmployee.setVisible(false);

            }
            ApplicationNavigator.listEmployeesController.populateTable(employees);
        }
        
        employeeCb.setValue(null);
    }
    
    @FXML
    private void displayEmployee(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.employee, employeeContent, 
                                                ApplicationNavigator.displayEmployeeController);
    }
    
    @FXML
    private void newEmployee(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addEmployees, employeeContent, 
                                            ApplicationNavigator.addEmployeeController);
        
    }
    
    @FXML
    private void deleteEmployee(){
        
    }
    
    
    // Vehicle Methods
    @FXML
    private void searchVehicle(){
        
    }
    
    @FXML
    private void displayVehicle(){
        
    }
    
    @FXML
    private void newVehicle(){
        
    }
    
    @FXML
    private void deleteVehicle(){
        
    }
    
    
    // Malfunction methods
    @FXML
    private void searchMalfunction(){
        
    }
    
    @FXML
    private void displayMalfunction(){
        
    }
    
    @FXML
    private void newMalfunction(){
        
    }
    
    @FXML
    private void deleteMalfunction(){
        
    }
    

    /**
     * This method is called by the Navigator to set the content of the
     * BorderPane. It's the function allowing for multiple nodes in the same
     * tab.
     *
     * @param screen
     * @param tab
     */
    public void setTabScreen(Node screen, Node tab) {
        String setTab = tab.getId();

        switch (setTab) {
            case "customerContent":
                customerContent.setCenter(screen);
                break;
            case "vehicleContent":
                vehicleContent.setCenter(screen);
                break;
            case "employeeContent":
                employeeContent.setCenter(screen);
                break;
            case "malfunctionsContent":
                malfunctionsContent.setCenter(screen);
                break;
            case "bookingContent":
                bookingContent.setCenter(screen);
                break;
            case "appointmentContent":
                appointmentContent.setCenter(screen);
                break;
            default:
                System.out.println("Oops setting the tab borked!");
        }

    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerCb.setItems(FXCollections.observableArrayList("Namn", "Efternamn", "Email", "Telefon", "Kundnr"));
        vehicleCb.setItems(FXCollections.observableArrayList("Nummerplåt", "Kundnr", "Märke", "Modell"));
        appointmentCb.setItems(FXCollections.observableArrayList("Kundnr", "Nummerplåt"));
        bookingCb.setItems(FXCollections.observableArrayList("Kundnr", "Nummerplåt"));
        employeeCb.setItems(FXCollections.observableArrayList("Anställningsnr", "Telefon"));
        malfunctionCb.setItems(FXCollections.observableArrayList("Datum", "Kundnr", "Nummerplåt"));
    }

}
