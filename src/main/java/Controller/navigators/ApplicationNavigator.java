/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.navigators;

import Controller.ApplicationController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Gemtastic
 */
public class ApplicationNavigator {

    public static String customer = "";
    public static String listCustomers = "/fxml/ListCustomers.fxml";

    
    private static ApplicationController controller;

//    private static 
    
    public static void setController(ApplicationController controller) {
        ApplicationNavigator.controller = controller;
    }

    public static void loadTabContent(String fxml, Node tab) {

        try {
            Node node = (Node) FXMLLoader.load(ApplicationNavigator.class.getResource(fxml));
            controller.setTabScreen(node, tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
