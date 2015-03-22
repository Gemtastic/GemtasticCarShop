/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.navigators;

import Controller.ApplicationController;
import Controller.malfunctions.ListMalfunctionController;
import Controller.appointments.ListAppointmentController;
import Controller.bookings.ListBookingController;
import Controller.cars.DisplayCarController;
import Controller.cars.ListCarController;
import Controller.customers.AddCustomerController;
import Controller.customers.DisplayCustomerController;
import Controller.customers.EditCustomerController;
import Controller.customers.ListCustomerController;
import Controller.employees.ListEmployeesController;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import org.jooq.Result;

/**
 *
 * @author Gemtastic
 */
public class ApplicationNavigator implements Initializable{

    public static String customer = "/fxml/DisplayCustomer.fxml";
    public static String listCustomers = "/fxml/ListCustomers.fxml";
    public static String editCustomers = "/fxml/EditCustomer.fxml";
    public static String addCustomers = "/fxml/AddCustomers.fxml";

    public static String vehicle = "/fxml/DisplayVehicle.fxml";
    public static String listVehicles = "/fxml/ListVehicles.fxml";
    public static String editVehicles = "";
    public static String addVehicles = "";

    public static String employee = "";
    public static String listEmployees = "/fxml/ListEmployees.fxml";
    public static String editEmployees = "";
    public static String addEmployees = "";

    public static String malfunction = "";
    public static String listMalfunctions = "/fxml/ListMalfunctions.fxml";
    public static String editMalfunctions = "";
    public static String addMalfunctions = "";

    public static String appointment = "";
    public static String listAppointments = "/fxml/ListAppointments.fxml";
    public static String editAppointments = "";
    public static String addAppointments = "";

    public static String bookings = "";
    public static String listBookings = "/fxml/ListBookings.fxml";
    public static String editBookings = "";
    public static String addBookings = "";

    // Attempting to store the controllers for mediating use
    public static ListCustomerController listCustomersController;
    public static DisplayCustomerController displayCustomersController;
    public static EditCustomerController editCustomersController;
    public static AddCustomerController addCustomersController;

    public static DisplayCarController displayCarController;
    public static ListCarController listCarController;

    public static ListAppointmentController listAppointmentController;

    public static ListBookingController listBookingController;

    public static ListEmployeesController listEmployeesController;

    public static ListMalfunctionController listMalfunctionController;

    public static ApplicationController controller;

//    private static 
    public static void setController(ApplicationController controller) {
        ApplicationNavigator.controller = controller;
        ApplicationNavigator.listCustomersController = new ListCustomerController();
        ApplicationNavigator.displayCustomersController = new DisplayCustomerController();
        ApplicationNavigator.editCustomersController = new EditCustomerController();
        ApplicationNavigator.addCustomersController = new AddCustomerController();

        ApplicationNavigator.displayCarController = new DisplayCarController();
        ApplicationNavigator.listCarController = new ListCarController();

        ApplicationNavigator.listAppointmentController = new ListAppointmentController();

        ApplicationNavigator.listBookingController = new ListBookingController();

        ApplicationNavigator.listEmployeesController = new ListEmployeesController();

        ApplicationNavigator.listMalfunctionController = new ListMalfunctionController();
    }

    public static void populateTable(Result<CustomerRecord> result) {
        listCustomersController.populateTable(result);
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

    public static void setActiveTab(Tab tab) {

        System.out.println("tab: " + tab);

        try {
            controller.tabPane.getTabs().add(controller.appointmentTab);
            controller.tabPane.getTabs().add(controller.bookingTab);
            controller.tabPane.getTabs().add(controller.customerTab);
            controller.tabPane.getTabs().add(controller.employeeTab);
            controller.tabPane.getTabs().add(controller.malfunctionTab);
            controller.tabPane.getTabs().add(controller.statisticTab);
            controller.tabPane.getTabs().add(controller.vehicleTab);

            SingleSelectionModel<Tab> selectionModel = controller.tabPane.getSelectionModel();

            System.out.println(selectionModel);

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
