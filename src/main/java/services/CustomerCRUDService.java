package services;

import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class CustomerCRUDService implements CRUDServices {

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    @Override
    public Result<CustomerRecord> getAll() {
        Result<CustomerRecord> customers = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            customers = create.fetch(CUSTOMER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public void create() {
//        CustomerRecord c = null;
                
//        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
//            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
//            c = create.newRecord(CUSTOMER);
//            c.setFirstName("Derpa");
//            c.setAddress(2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public CustomerRecord read(int customer) {
        CustomerRecord c = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            c = create.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(customer)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void delete(int id) {
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
