package application;

import Controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Gemtastic on 2015-03-04.
 */
public class ApplicationMain extends Application {
    public static void main(String... ignored){
        launch(ignored);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setController(new LoginController());
        loader.setLocation(ApplicationMain.class.getResource("/fxml/LogIn.fxml"));
        
        AnchorPane root = loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
