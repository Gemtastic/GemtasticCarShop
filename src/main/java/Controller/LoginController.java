package Controller;

import Controller.navigators.ApplicationNavigator;
import application.ApplicationMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Gemtastic
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button loginbtn;
    
    @FXML
    private void loginAttempt() throws Exception{
        
        // TO DO add verification calls/hashing
        
        Stage stage = new Stage();
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setController(new ApplicationController());
        loader.setLocation(ApplicationMain.class.getResource("/fxml/Application.fxml"));
        
        AnchorPane root = loader.load();
        
        ApplicationController applicationController = loader.getController();
        ApplicationNavigator.setController(applicationController);
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listCustomers, 
                                            applicationController.customerContent,
                                            ApplicationNavigator.listCustomersController);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listBookings, 
                                            ApplicationNavigator.controller.bookingContent,
                                            ApplicationNavigator.listBookingController);
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listAppointments, 
                                            ApplicationNavigator.controller.appointmentContent,
                                            ApplicationNavigator.listAppointmentController);
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listVehicles, 
                                            ApplicationNavigator.controller.vehicleContent,
                                            ApplicationNavigator.listCustomersController);
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listEmployees, 
                                            ApplicationNavigator.controller.employeeContent,
                                            ApplicationNavigator.listEmployeesController);
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listMalfunctions, 
                                            ApplicationNavigator.controller.malfunctionsContent,
                                            ApplicationNavigator.listMalfunctionController);
        
        Stage oldStage = (Stage)loginbtn.getScene().getWindow();
        oldStage.close();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
