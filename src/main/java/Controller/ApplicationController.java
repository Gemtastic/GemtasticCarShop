package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Controller.customers.ListCustomerController;
import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.CustomerRecord;
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
import services.CRUD.CustomerCRUDService;
import services.CustomerSearchService;

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
            ApplicationNavigator.populateTable(customers);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else {
            customers = service.getAllWhere(column, search);

            if (customers == null) {
                errorSearchCustomer.setVisible(true);
            } else {
                errorSearchCustomer.setVisible(false);

            }
            ApplicationNavigator.populateTable(customers);
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
        CustomerCRUDService service = new CustomerCRUDService();
        CustomerRecord customer = ApplicationNavigator.getSelectedCustomer();
        if (customer != null) {
            service.delete(customer.getId());
            
            ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, customerContent, 
                                                ApplicationNavigator.listCustomersController);
        }
    }
    
    
    @FXML
    private void searchBookings(){
        showBookingBtn.setDisable(false);

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
            ApplicationNavigator.populateTable(customers);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else {
            customers = service.getAllWhere(column, search);

            if (customers == null) {
                errorSearchCustomer.setVisible(true);
            } else {
                errorSearchCustomer.setVisible(false);

            }
            ApplicationNavigator.populateTable(customers);
        }
        
        customerCb.setValue(null);
    }
    
    @FXML
    private void displayBooking(){
        
    }
    
    @FXML
    private void newAppointment(){
        
    }
    
    @FXML
    private void deleteBooking(){
        
    }
    
    
    @FXML
    private void searchAppointment(){
        
    }
    
    @FXML
    private void displayAppointment(){
        
    }
    
    @FXML
    private void deleteAppointment(){
        
    }
    
    
    @FXML
    private void searchEmployee(){
        
    }
    
    @FXML
    private void displayEmployee(){
        
    }
    
    @FXML
    private void newEmployee(){
        
    }
    
    @FXML
    private void deleteEmployee(){
        
    }
    
    
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
        appointmentCb.setItems(FXCollections.observableArrayList("Datum", "Kundnr", "Nummerplåt"));
        bookingCb.setItems(FXCollections.observableArrayList("Datum", "Kundnr", "Nummerplåt"));
        employeeCb.setItems(FXCollections.observableArrayList("Användarnamn", "Anställningsnr", "Telefon"));
        malfunctionCb.setItems(FXCollections.observableArrayList("Datum", "Kundnr", "Nummerplåt"));
    }

}
