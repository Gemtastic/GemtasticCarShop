package services;

import com.gemtastic.carshop.tables.records.EmployeesRecord;
import org.mindrot.jbcrypt.BCrypt;
import services.interfaces.LogInServices;

/**
 *
 * @author Gemtastic
 */
public class LogInService implements LogInServices {

    @Override
    public boolean verify(String username, String password) {
        boolean success = false;
        
        EmployeeSearchService service = new EmployeeSearchService();
        
        EmployeesRecord employee = new EmployeesRecord();
        
        // TODO this
        
        
        return success;
    }

    @Override
    public String hash(String password) {
        
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);
        
        return hashed;
    }
    
}
