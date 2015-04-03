package Controller;

import AlternativeRecords.AppointmentAppointmentsRecord;
import AlternativeRecords.AppointmentBookingRecord;
import AlternativeRecords.VehicleRecord;
import services.AppointmentSearchService;
import Controller.customers.ListCustomerController;
import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
import services.CRUD.AppointmentCRUDService;
import services.CRUD.CarCRUDService;
import services.CRUD.CustomerCRUDService;
import services.CRUD.EmployeeCRUDService;
import services.CRUD.MalfunctionCRUDService;
import services.CarSearchService;
import services.CustomerSearchService;
import services.EmployeeSearchService;
import services.MalfunctionSearchService;

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
    private Button editAppointmentBtn;
    
    @FXML
    private Button deleteAppointmentBtn;
    
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
    private Button confirmBtn;
    
    @FXML
    private Button deleteBookingBtn;
    
    @FXML
    private Button editBookingBtn;
    
    @FXML
    private TextField bookingSearchField;
    
    @FXML
    private ChoiceBox bookingCb;
    
    @FXML
    private Label errorSearchBooking;
    
    @FXML
    private Label errorupdate;
    
    
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
    private Button editMalfunctionBtn;
    
    @FXML
    private TextField malfunctionSearchField;
    
    @FXML
    private ChoiceBox malfunctionCb;
    
    @FXML
    private Label errorSearchMalfunction;
    
    
    // Statistics tab, methods and data
    @FXML
    public BorderPane statisticsContent;
    
    
    
    @FXML
    private void searchCustomer() {
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

            if (customers == null || customers.isEmpty()) {
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

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, bookingContent,
                                            ApplicationNavigator.listBookingController);

        AppointmentSearchService service = new AppointmentSearchService();
        CarSearchService carService = new CarSearchService();
        
        Result<AppointmentsRecord> appointments = null;
        List<AppointmentBookingRecord> bookings = FXCollections.observableArrayList();
        String search = bookingSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = bookingCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (bookingSearchField.getText().isEmpty() && column == null) {
            appointments = service.getAllWhere("booking", "0");
            errorSearchBooking.setVisible(false);
            bookings = ApplicationNavigator.listBookingController.getAsBookingList(appointments);
            ApplicationNavigator.listBookingController.populateTable(bookings);
        } else if (!bookingSearchField.getText().isEmpty() && column == null) {
            errorSearchBooking.setVisible(true);
            ApplicationNavigator.listBookingController.populateTable(bookings);
        } else if (bookingSearchField.getText().isEmpty() && column != null) {
            errorSearchBooking.setVisible(true);
            ApplicationNavigator.listBookingController.populateTable(bookings);
        } else {
            
            appointments = service.getAllWhere(column, search);

            if (appointments == null || appointments.isEmpty()) {
                errorSearchBooking.setVisible(true);
            } else {
                errorSearchBooking.setVisible(false);
            }
            
            bookings = ApplicationNavigator.listBookingController.getAsBookingList(appointments);
            ApplicationNavigator.listBookingController.populateTable(bookings);
        }
        
        bookingCb.setValue(null);
    }
    
    @FXML
    private void editBooking(){
        
        AppointmentsRecord selected = ApplicationNavigator.listBookingController.getSelected();
        
        if(selected != null){
            ApplicationNavigator.loadTabContent(ApplicationNavigator.editAppointments, bookingContent,
                                                ApplicationNavigator.editAppointmentController);
            ApplicationNavigator.editAppointmentController.loadBooking(selected);
        }
    }
    
    @FXML
    private void deleteBooking(){
        AppointmentCRUDService appService = new AppointmentCRUDService();
        
        AppointmentsRecord app = ApplicationNavigator.listBookingController.getSelected();
        
        if (app != null) {
            appService.delete(app.getId());
            
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, bookingContent, 
                                                ApplicationNavigator.listBookingController);
        }
    }
    
    @FXML
    private void setAppointmentDate(){
        errorupdate.setVisible(false);
        
        AppointmentCRUDService service = new AppointmentCRUDService();
        
        AppointmentsRecord r = ApplicationNavigator.listBookingController.getSelected();
        if(r != null){
            r.setPerformedDate(Timestamp.valueOf(LocalDateTime.now()));
            boolean updated = service.update(r);

            if(!updated){
                errorupdate.setVisible(true);
            }
        }
    }
    
    // Appointments and Bookings are the same in the DB
    @FXML
    private void newAppointment(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addBookings, bookingContent,
                                            ApplicationNavigator.addBookingController);
        ApplicationNavigator.setActiveTab(bookingTab);
    }
    
    // Appointment methods
    @FXML
    private void editAppointment(){
        
        AppointmentAppointmentsRecord selected = ApplicationNavigator.listAppointmentController.getSelected();
        
        if(selected != null){
            ApplicationNavigator.loadTabContent(ApplicationNavigator.editAppointments, appointmentContent,
                                                ApplicationNavigator.editAppointmentController);
            ApplicationNavigator.editAppointmentController.loadAppointment(selected.getAppointmentRecord());
        }
    }
    
    @FXML
    private void searchAppointment(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listAppointments, appointmentContent,
                                            ApplicationNavigator.listAppointmentController);

        AppointmentSearchService service = new AppointmentSearchService();
        CarSearchService carService = new CarSearchService();
        
        Result<AppointmentsRecord> appointments = null;
        List<AppointmentAppointmentsRecord> app = FXCollections.observableArrayList();
        String search = appointmentSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = appointmentCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (appointmentSearchField.getText().isEmpty() && column == null) {
            appointments = service.getAllWhere("appointment", "0");
            errorSearchAppointment.setVisible(false);
            app = ApplicationNavigator.listAppointmentController.getAsAppointment(appointments);
            ApplicationNavigator.listAppointmentController.populateTable(app);
        } else if (!appointmentSearchField.getText().isEmpty() && column == null) {
            errorSearchAppointment.setVisible(true);
            ApplicationNavigator.listAppointmentController.populateTable(app);
        } else if (appointmentSearchField.getText().isEmpty() && column != null) {
            errorSearchAppointment.setVisible(true);
            ApplicationNavigator.listAppointmentController.populateTable(app);
        } else {
            
            appointments = service.getAllWhere(column, search);

            if (appointments == null || appointments.isEmpty()) {
                errorSearchAppointment.setVisible(true);
            } else {
                errorSearchAppointment.setVisible(false);
            }
            
            app = ApplicationNavigator.listAppointmentController.getAsAppointment(appointments);
            ApplicationNavigator.listAppointmentController.populateTable(app);
        }
        
        appointmentCb.setValue(null);
    }
    
    @FXML
    private void deleteAppointment(){
        AppointmentCRUDService appService = new AppointmentCRUDService();
        
        AppointmentAppointmentsRecord app = ApplicationNavigator.listAppointmentController.getSelected();
        
        if (app != null) {
            AppointmentsRecord record = appService.read(app.getId());
            appService.delete(record.getId());
            
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listAppointments, appointmentContent, 
                                                ApplicationNavigator.listAppointmentController);
        }
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

            if (employees == null || employees.isEmpty()) {
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
        EmployeesRecord employee = ApplicationNavigator.listEmployeesController.getSelected();
        if(employee != null){
            showEmployeeBtn.setDisable(true);
            ApplicationNavigator.loadTabContent(ApplicationNavigator.employee, employeeContent, 
                                                ApplicationNavigator.displayEmployeeController);
        
            ApplicationNavigator.displayEmployeeController.loadEmployee(employee);
        }
        
    }
    
    @FXML
    private void newEmployee(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addEmployees, employeeContent, 
                                            ApplicationNavigator.addEmployeeController);
        
    }
    
    @FXML
    private void deleteEmployee(){
        EmployeeCRUDService delete = new EmployeeCRUDService();
        
        EmployeesRecord employee = ApplicationNavigator.listEmployeesController.getSelected();
        if(employee != null){
            delete.delete(employee.getId());
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listEmployees, employeeContent,
                                                ApplicationNavigator.listEmployeesController);
        }
    }
    
    
    // Vehicle Methods
    @FXML
    private void searchVehicle(){
        showCarBtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listVehicles, vehicleContent,
                                            ApplicationNavigator.listCarController);

        CarSearchService service = new CarSearchService();
        Result<CarRecord> vehicles = null;
        List<VehicleRecord> cars = FXCollections.observableArrayList();
        String search = vehicleSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = vehicleCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (vehicleSearchField.getText().isEmpty() && column == null) {
            vehicles = service.getAll();
            errorSearchVehicle.setVisible(false);
            cars = ApplicationNavigator.listCarController.getAsVehicleRecord(vehicles);
            ApplicationNavigator.listCarController.populateTable(cars);
        } else if (!vehicleSearchField.getText().isEmpty() && column == null) {
            errorSearchVehicle.setVisible(true);
            ApplicationNavigator.listCarController.populateTable(cars);
        } else if (vehicleSearchField.getText().isEmpty() && column != null) {
            errorSearchVehicle.setVisible(true);
            ApplicationNavigator.listCarController.populateTable(cars);
        } else {
            vehicles = service.getAllWhere(column, search);
            
            if (vehicles == null || vehicles.isEmpty()) {
                errorSearchVehicle.setVisible(true);
            } else {
                errorSearchVehicle.setVisible(false);

            }
            cars = ApplicationNavigator.listCarController.getAsVehicleRecord(vehicles);
            ApplicationNavigator.listCarController.populateTable(cars);
        }
        
        vehicleCb.setValue(null);
    }
    
    @FXML
    private void displayVehicle(){
        CarRecord car = ApplicationNavigator.listCarController.getSelected();
        
        if(car != null){
            showCarBtn.setDisable(true);
            ApplicationNavigator.loadTabContent(ApplicationNavigator.vehicle,
                                            ApplicationNavigator.controller.vehicleContent,
                                            ApplicationNavigator.displayCarController);
            ApplicationNavigator.displayCarController.loadCar(car);
        }
    }
    
    @FXML
    private void newVehicle(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addVehicles, vehicleContent,
                                            ApplicationNavigator.addCarController);
    }
    
    @FXML
    private void deleteVehicle(){
        CarCRUDService delete = new CarCRUDService();
        
        CarRecord vehicle = ApplicationNavigator.listCarController.getSelected();
        
        if(vehicle != null){
            delete.delete(vehicle.getId());
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listVehicles, vehicleContent,
                                                ApplicationNavigator.listCarController);
        }
    }
    
    
    // Malfunction methods
    @FXML
    private void searchMalfunction(){
        editMalfunctionBtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, malfunctionsContent,
                                            ApplicationNavigator.listMalfunctionController);

        MalfunctionSearchService service = new MalfunctionSearchService();
        Result<MalfunctionReportsRecord> malfunctions = null;
        String search = customerSearchField.getText();

        // Ugly fix; customerCb returns an object and adding the toString() 
        // method didn't work, but this does
        Object cb = malfunctionCb.getSelectionModel().getSelectedItem();
        String column = null;
        if (cb != null) {
            column = cb.toString();
        }

        // Search and display result or error message
        if (malfunctionSearchField.getText().isEmpty() && column == null) {
            malfunctions = service.getAll();
            errorSearchMalfunction.setVisible(false);
            ApplicationNavigator.listMalfunctionController.populateTable(malfunctions);
        } else if (!malfunctionSearchField.getText().isEmpty() && column == null) {
            errorSearchMalfunction.setVisible(true);
            ApplicationNavigator.listMalfunctionController.populateTable(malfunctions);
        } else if (malfunctionSearchField.getText().isEmpty() && column != null) {
            errorSearchMalfunction.setVisible(true);
            ApplicationNavigator.listMalfunctionController.populateTable(malfunctions);
        } else {
            malfunctions = service.getAllWhere(column, search);

            if (malfunctions == null || malfunctions.isEmpty()) {
                errorSearchMalfunction.setVisible(true);
            } else {
                errorSearchMalfunction.setVisible(false);

            }
            ApplicationNavigator.listMalfunctionController.populateTable(malfunctions);
        }
        
        malfunctionCb.setValue(null);
    }
    
    @FXML
    private void editMalfunction(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.editMalfunctions, malfunctionsContent,
                                            ApplicationNavigator.editMalfunctionController);
         ApplicationNavigator.editMalfunctionController.loadReport(ApplicationNavigator.listMalfunctionController.getSelected());
    }
    
    @FXML
    private void newMalfunction(){
        ApplicationNavigator.loadTabContent(ApplicationNavigator.addMalfunctions, malfunctionsContent,
                                            ApplicationNavigator.addMalfunctionController);
    }
    
    @FXML
    private void deleteMalfunction(){
        MalfunctionCRUDService delete = new MalfunctionCRUDService();
        
        MalfunctionReportsRecord malfunction = ApplicationNavigator.listMalfunctionController.getSelected();
        if(malfunction != null){
            delete.delete(malfunction.getId());
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, malfunctionsContent,
                                                ApplicationNavigator.listMalfunctionController);
        }
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
            case "statisticsContent":
                statisticsContent.setCenter(screen);
                break;
            default:
                System.out.println("Oops setting the tab borked!");
        }
    }
    
    public void cleanSlate(){
        errorSearchAppointment.setVisible(false);
        errorSearchBooking.setVisible(false);
        errorSearchCustomer.setVisible(false);
        errorSearchEmployee.setVisible(false);
        errorSearchMalfunction.setVisible(false);
        errorSearchVehicle.setVisible(false);
        errorupdate.setVisible(false);
        
        appointmentSearchField.setText("");
        employeeSearchField.setText("");
        vehicleSearchField.setText("");
        bookingSearchField.setText("");
        customerSearchField.setText("");
        malfunctionSearchField.setText("");
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
        malfunctionCb.setItems(FXCollections.observableArrayList("Datum", "Nummerplåt"));
        
        errorupdate.setVisible(false);
    }

}
