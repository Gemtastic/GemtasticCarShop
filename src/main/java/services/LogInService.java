package services;

import com.gemtastic.carshop.tables.records.EmployeesRecord;
import org.jooq.Result;
import org.mindrot.jbcrypt.BCrypt;
import services.interfaces.LogInServices;

/**
 *
 * @author Gemtastic
 */
public class LogInService implements LogInServices {
    
    @Override
    public boolean verify(String enteredun, String enteredpw) {
        boolean success = false;
        
        EmployeeSearchService search = new EmployeeSearchService();
        
        Result<EmployeesRecord> employee = search.getAllWhere("username", enteredun);
        
        if(employee.isNotEmpty()){
            EmployeesRecord r = employee.get(0);
            
            if(r != null){
                String password = r.getPassword();
                success = BCrypt.checkpw(enteredpw, password);
            }
        }
        
        return success;
    }

    @Override
    public String hash(String password) {
        
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);
        
        return hashed;
    }
    
}
