package services.CRUD;

import static Controller.navigators.ApplicationNavigator.customer;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import static com.gemtastic.carshop.tables.Employees.EMPLOYEES;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class EmployeeCRUDService implements CRUDServices<EmployeesRecord>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public EmployeesRecord create(EmployeesRecord t) {
        EmployeesRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            r = create.newRecord(EMPLOYEES);
            r.setEmail(t.getEmail());
            r.setPassword(t.getPassword());
            r.setUsername(t.getUsername());
            r.setPhone(t.getPhone());
            r.store();
            
            // Get the (possibly) auto-generated ID from the record
            Integer id = r.getId();
            r.setId(id);
            r.store();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public EmployeesRecord read(int t) {
        EmployeesRecord employee = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.ID.eq(t)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employee;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.delete(EMPLOYEES).where(EMPLOYEES.ID.eq(id)).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(EmployeesRecord t) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(EMPLOYEES)
                    .set(EMPLOYEES.EMAIL, t.getEmail())
                    .set(EMPLOYEES.PASSWORD, t.getPassword())
                    .set(EMPLOYEES.PHONE, t.getPhone())
                    .set(EMPLOYEES.USERNAME, t.getUsername())
                    .where(EMPLOYEES.ID.equal(t.getId()))
                    .execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
}
