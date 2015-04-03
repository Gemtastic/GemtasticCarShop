package Controller.navigators;

import Controller.ApplicationController;
import Controller.appointments.EditAppointmentController;
import Controller.malfunctions.ListMalfunctionController;
import Controller.appointments.ListAppointmentController;
import Controller.bookings.AddBookingController;
import Controller.bookings.ListBookingController;
import Controller.cars.AddCarController;
import Controller.cars.DisplayCarController;
import Controller.cars.ListCarController;
import Controller.cars.OwnershipController;
import Controller.customers.AddCustomerController;
import Controller.customers.DisplayCustomerController;
import Controller.customers.EditCustomerController;
import Controller.customers.ListCustomerController;
import Controller.employees.AddEmployeeController;
import Controller.employees.DisplayEmployeeController;
import Controller.employees.EditEmployeeController;
import Controller.employees.ListEmployeesController;
import Controller.malfunctions.AddMalfunctionReportsController;
import Controller.malfunctions.EditMalfunctionController;
import Controller.statistics.StatisticsController;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;

/**
 *
 * @author Gemtastic
 */
public class ApplicationNavigator implements Initializable{

    public static String customer = "/fxml/DisplayCustomer.fxml";
    public static String listCustomers = "/fxml/ListCustomers.fxml";
    public static String editCustomers = "/fxml/EditCustomer.fxml";
    public static String addCustomers = "/fxml/AddCustomer.fxml";
    
    public static String ownership = "/fxml/EditOwnership.fxml";
    
    public static String statistics = "/fxml/ShowStatistics.fxml";

    public static String vehicle = "/fxml/DisplayVehicle.fxml";
    public static String listVehicles = "/fxml/ListVehicles.fxml";
    public static String editVehicles = "";
    public static String addVehicles = "/fxml/AddCar.fxml";

    public static String employee = "/fxml/DisplayEmployee.fxml";
    public static String listEmployees = "/fxml/ListEmployees.fxml";
    public static String editEmployees = "/fxml/EditEmployee.fxml";
    public static String addEmployees = "/fxml/AddEmployee.fxml";

    public static String listMalfunctions = "/fxml/ListMalfunctions.fxml";
    public static String editMalfunctions = "/fxml/EditMalfunctionReport.fxml";
    public static String addMalfunctions = "/fxml/AddMalfunctionReport.fxml";

    public static String listAppointments = "/fxml/ListAppointments.fxml";
    public static String editAppointments = "/fxml/EditAppointment.fxml";
    public static String addAppointments = "/fxml/AddBooking.fxml";

    public static String listBookings = "/fxml/ListBookings.fxml";
    public static String editBookings = "/fxml/EditAppointment.fxml";
    public static String addBookings = "/fxml/AddBooking.fxml";

    // Attempting to store the controllers for mediating use
    public static ListCustomerController listCustomersController;
    public static DisplayCustomerController displayCustomersController;
    public static EditCustomerController editCustomersController;
    public static AddCustomerController addCustomersController;
    
    public static OwnershipController editOwnershipController;
    
    public static StatisticsController statisticsController;

    public static DisplayCarController displayCarController;
    public static ListCarController listCarController;
    public static AddCarController addCarController;

    public static ListAppointmentController listAppointmentController;
    public static EditAppointmentController editAppointmentController;

    public static ListBookingController listBookingController;
    public static AddBookingController addBookingController;

    public static ListEmployeesController listEmployeesController;
    public static AddEmployeeController addEmployeeController;
    public static DisplayEmployeeController displayEmployeeController;
    public static EditEmployeeController editEmployeeController;

    public static ListMalfunctionController listMalfunctionController;
    public static AddMalfunctionReportsController addMalfunctionController;
    public static EditMalfunctionController editMalfunctionController;

    public static ApplicationController controller;

//    private static 
    public static void setController(ApplicationController controller) {
        ApplicationNavigator.controller = controller;
        ApplicationNavigator.listCustomersController = new ListCustomerController();
        ApplicationNavigator.displayCustomersController = new DisplayCustomerController();
        ApplicationNavigator.editCustomersController = new EditCustomerController();
        ApplicationNavigator.addCustomersController = new AddCustomerController();
        
        ApplicationNavigator.editOwnershipController = new OwnershipController();
        
        ApplicationNavigator.statisticsController = new StatisticsController();

        ApplicationNavigator.displayCarController = new DisplayCarController();
        ApplicationNavigator.listCarController = new ListCarController();
        ApplicationNavigator.addCarController = new AddCarController();

        ApplicationNavigator.listAppointmentController = new ListAppointmentController();
        ApplicationNavigator.editAppointmentController = new EditAppointmentController();

        ApplicationNavigator.listBookingController = new ListBookingController();
        ApplicationNavigator.addBookingController = new AddBookingController();

        ApplicationNavigator.listEmployeesController = new ListEmployeesController();
        ApplicationNavigator.addEmployeeController = new AddEmployeeController();
        ApplicationNavigator.displayEmployeeController = new DisplayEmployeeController();
        ApplicationNavigator.editEmployeeController = new EditEmployeeController();

        ApplicationNavigator.listMalfunctionController = new ListMalfunctionController();
        ApplicationNavigator.addMalfunctionController = new AddMalfunctionReportsController();
        ApplicationNavigator.editMalfunctionController = new EditMalfunctionController(); 
    }

    /**
     * This method is in charge of setting the content of a node and sending it
     * to the controller which will set the panes.
     *
     * @param fxml
     * @param tabBorderPane
     * @param i
     */
    public static void loadTabContent(String fxml, Node tabBorderPane, Initializable i) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(i);
            loader.setLocation(ApplicationNavigator.class.getResource(fxml));
            Node centerContent = loader.load();

            controller.setTabScreen(centerContent, tabBorderPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method moves to the new tab where things are happening.
     * 
     * @param tab 
     */
    public static void setActiveTab(Tab tab) {

        try {
            controller.tabPane.getTabs().add(controller.appointmentTab);
            controller.tabPane.getTabs().add(controller.bookingTab);
            controller.tabPane.getTabs().add(controller.customerTab);
            controller.tabPane.getTabs().add(controller.employeeTab);
            controller.tabPane.getTabs().add(controller.malfunctionTab);
            controller.tabPane.getTabs().add(controller.statisticTab);
            controller.tabPane.getTabs().add(controller.vehicleTab);

            SingleSelectionModel<Tab> selectionModel = controller.tabPane.getSelectionModel();

            selectionModel.select(tab);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static CustomerRecord getSelectedCustomer() {
        CustomerRecord record = listCustomersController.getSelected();

        return record;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
