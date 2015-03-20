/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.navigators;

import Controller.ApplicationController;
import Controller.customers.AddCustomerController;
import Controller.customers.DisplayCustomerController;
import Controller.customers.EditCustomerController;
import Controller.customers.ListCustomerController;
import application.DatabaseContextProvider;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import org.jooq.DSLContext;
import org.jooq.Result;
import services.CustomerCRUDService;

/**
 *
 * @author Gemtastic
 */
public class ApplicationNavigator {

    public static String customer = "/fxml/DisplayCustomer.fxml";
    public static String listCustomers = "/fxml/ListCustomers.fxml";
    public static String editCustomers = "/fxml/EditCustomer.fxml";
    public static String addCustomers = "/fxml/AddCustomers.fxml";
    
    public static String vehicle = "";
    public static String listVehicles = "";
    public static String editVehicles = "";
    public static String vehicles = "";
    
    // Attempting to store the controllers for mediating use
    public static ListCustomerController listCustomersController;
    public static DisplayCustomerController displayCustomersController;
    public static EditCustomerController editCustomersController;
    public static AddCustomerController addCustomersController;
    
    
    public static ApplicationController controller;

//    private static 
    public static void setController(ApplicationController controller, DatabaseContextProvider provider) throws SQLException {

        final DSLContext create = provider.getDslContext("jdbc:postgres:postgres", "postgres", "g3mt45t1c");

        // Todo: Instantiate services **HERE**. Pass the DSLContext above to those who need it.

        ApplicationNavigator.controller = controller;
        ApplicationNavigator.listCustomersController = new ListCustomerController();
        ApplicationNavigator.displayCustomersController = new DisplayCustomerController();
        ApplicationNavigator.editCustomersController = new EditCustomerController();
        ApplicationNavigator.addCustomersController = new AddCustomerController();
    }
    
    public static void populateTable(Result<CustomerRecord> result){
        listCustomersController.populateTable(result);
    }
    
    /**
     * This method is in charge of 
     * @param fxml
     * @param tab 
     * @param i 
     */
    public static void loadTabContent(String fxml, Node tab, Initializable i) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(i);
            loader.setLocation(ApplicationNavigator.class.getResource(fxml));
            Node node = loader.load();
            
            controller.setTabScreen(node, tab);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static CustomerRecord getSelectedCustomer(){
        CustomerRecord record = listCustomersController.getSelected();
        
        return record;
    }
}
