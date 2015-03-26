package application;

import Controller.LoginController;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jooq.Result;
import services.CRUD.EmployeeCRUDService;
import services.EmployeeSearchService;

/**
 * Created by Gemtastic on 2015-03-04.
 */
public class ApplicationMain extends Application {
    public static void main(String... ignored){
        launch(ignored);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        EmployeeSearchService service = new EmployeeSearchService();
        EmployeeCRUDService crud = new EmployeeCRUDService();
        
        Result<EmployeesRecord> r = service.getAll();
        
        if(r.isEmpty()){
            EmployeesRecord admin = new EmployeesRecord();
            admin.setEmail("Admin@Carshop.com");
            admin.setPhone("0000000");
            admin.setUsername("admin");
            admin.setPassword("***"); // This is not the real pw
            
            crud.create(admin);
        }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setController(new LoginController());
        loader.setLocation(ApplicationMain.class.getResource("/fxml/LogIn.fxml"));
        
        AnchorPane root = loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/img/logo.png"));
        stage.setTitle("Välkommen till CarShop!");
        stage.show();
    }
}
