package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Gemtastic
 */
public class ApplicationController implements Initializable {
    
    
    
    @FXML
    private ChoiceBox cb;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO make an observable list out of the customer table
        
//        cb.setItems("Namn", "Efternamn", "Kundnr", "");
    }    
    
}
