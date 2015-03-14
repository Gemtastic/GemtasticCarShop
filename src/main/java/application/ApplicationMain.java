package application;

import Controller.ViewController;
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
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setController(new ViewController());
        loader.setLocation(ApplicationMain.class.getResource("/fxml/View.fxml"));
        
        AnchorPane root = loader.load();
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
