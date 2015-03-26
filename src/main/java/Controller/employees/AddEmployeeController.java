package Controller.employees;

import Controller.navigators.ApplicationNavigator;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.CRUD.EmployeeCRUDService;
import services.LogInService;

/**
 *
 * @author Gemtastic
 */
public class AddEmployeeController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private Label error;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    private Button addUserBtn;

    @FXML
    private Button cancelUserBtn;

    @FXML
    private void addUser() {
        passwordErrorMessage.setVisible(false);

        if (!password.getText().isEmpty() || !username.getText().isEmpty()
                || !phone.getText().isEmpty() || !email.getText().isEmpty()
                || !repeatPassword.getText().isEmpty()) {

            EmployeesRecord record = new EmployeesRecord();
            EmployeeCRUDService service = new EmployeeCRUDService();
            LogInService hashService = new LogInService();

            if (repeatPassword.getText().equals(password.getText())) {

                record.setEmail(email.getText());
                record.setUsername(username.getText());
                record.setPhone(phone.getText());

                String hashedPW = hashService.hash(password.getText());

                record.setPassword(hashedPW);
                
                service.create(record);
                
                ApplicationNavigator.loadTabContent(ApplicationNavigator.listEmployees,
                                            ApplicationNavigator.controller.employeeContent,
                                            ApplicationNavigator.listEmployeesController);

            } else {
                passwordErrorMessage.setVisible(true);
            }

        }

    }

    @FXML
    private void cancelUser() {
        ApplicationNavigator.loadTabContent(ApplicationNavigator.listEmployees,
                                            ApplicationNavigator.controller.employeeContent,
                                            ApplicationNavigator.listEmployeesController);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordErrorMessage.setVisible(false);
        error.setVisible(false);
    }

}
