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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jooq.Result;
import services.CustomerSearchService;

/**
 * FXML Controller class
 *
 * @author Gemtastic
 */
public class ApplicationController implements Initializable {

    ListCustomerController customersView;

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

    private CustomerSearchService customerSearchService;

    public ApplicationController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @FXML
    private void searchCustomer() throws IOException {
        showCustomerbtn.setDisable(false);

        ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, customerContent,
                ApplicationNavigator.listCustomersController);

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
            customers = customerSearchService.getAll();
            errorSearchCustomer.setVisible(false);
            ApplicationNavigator.populateTable(customers);
        } else if (!customerSearchField.getText().isEmpty() && column == null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else if (customerSearchField.getText().isEmpty() && column != null) {
            errorSearchCustomer.setVisible(true);
            ApplicationNavigator.populateTable(customers);
        } else {
            customers = customerSearchService.getAllWhere(column, search);

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
    private void showCustomer() {
        CustomerRecord customer = ApplicationNavigator.getSelectedCustomer();
        if (customer != null) {
            ApplicationNavigator.loadTabContent(ApplicationNavigator.customer, customerContent, 
                                                ApplicationNavigator.displayCustomersController);
            ApplicationNavigator.displayCustomersController.loadCustomer(customer);

            showCustomerbtn.setDisable(true);
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
        System.out.println(setTab + "Is the tab being modified!");

        switch (setTab) {
            case "customerContent":
                customerContent.setCenter(screen);
                break;
            default:
                System.out.println("Oops this borked!");
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerCb.setItems(FXCollections.observableArrayList("Namn", "Efternamn", "Email", "Telefon", "Kundnr"));
    }

}
