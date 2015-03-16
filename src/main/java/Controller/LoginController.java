package Controller;

import application.ApplicationMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Gemtastic
 */
public class LoginController {
    
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
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        Stage oldStage = (Stage)loginbtn.getScene().getWindow();
        oldStage.close();
        
    }
}
