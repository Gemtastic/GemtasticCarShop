/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.navigators;

import Controller.ApplicationController;
import Controller.DisplayCustomerController;
import Controller.ListCustomerController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Gemtastic
 */
public class ApplicationNavigator {

    public static String customer = "/fxml/DisplayCustomer.fxml";
    public static String listCustomers = "/fxml/ListCustomers.fxml";
    
    // Attempting to store the controllers for mediating use
    private static ListCustomerController listCustomersController;
    private static DisplayCustomerController displayCustomersCOntroller;
    
    private static ApplicationController controller;

//    private static 
    
    public static void setController(ApplicationController controller) {
        ApplicationNavigator.controller = controller;
        ApplicationNavigator.listCustomersController = new ListCustomerController();
    }

    public static void loadTabContent(String fxml, Node tab) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(listCustomersController);
            loader.setLocation(ApplicationNavigator.class.getResource(ApplicationNavigator.listCustomers));
            Node node = loader.load();
            
            
            
            controller.setTabScreen(node, tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
