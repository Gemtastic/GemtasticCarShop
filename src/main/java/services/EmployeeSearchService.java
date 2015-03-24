package services;

import static Controller.navigators.ApplicationNavigator.customer;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import static com.gemtastic.carshop.tables.Employees.EMPLOYEES;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.SearchServices;

/**
 *
 * @author Gemtastic
 */
public class EmployeeSearchService implements SearchServices<Result<EmployeesRecord>>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<EmployeesRecord> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result<EmployeesRecord> getAllWhere(String column, String constraint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public EmployeesRecord getByUsername(String username) {
        EmployeesRecord employee = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.USERNAME.eq(username)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employee;
    }
}
