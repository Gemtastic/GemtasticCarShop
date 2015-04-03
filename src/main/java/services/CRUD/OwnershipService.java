package services.CRUD;

import static com.gemtastic.carshop.tables.Ownership.OWNERSHIP;
import com.gemtastic.carshop.tables.records.OwnershipRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author Gemtastic
 */
public class OwnershipService {
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    public OwnershipRecord create(OwnershipRecord o) {
        OwnershipRecord owner = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            create.insertInto(OWNERSHIP).set(o).execute();
            owner = o;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return owner;
    }

    public void delete(OwnershipRecord o) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            create.delete(OWNERSHIP)
                    .where(OWNERSHIP.OWNER.eq(o.getOwner()).and(OWNERSHIP.CAR.eq(o.getCar())))
                    .execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
