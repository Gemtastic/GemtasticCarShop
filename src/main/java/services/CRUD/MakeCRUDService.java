package services.CRUD;

import static Controller.navigators.ApplicationNavigator.customer;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import static com.gemtastic.carshop.tables.Make.MAKE;
import com.gemtastic.carshop.tables.records.MakeRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Aizic Moisen
 */
public class MakeCRUDService implements CRUDServices<MakeRecord>{
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    @Override
    public MakeRecord create(MakeRecord t) {
        MakeRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            MakeRecord existing = create.selectFrom(MAKE).where(MAKE.MAKE_.eq(t.getMake())).fetchOne();
            
            if(existing == null){
                r = create.newRecord(MAKE);
                r.setMake(t.getMake());
                r.store();
                
                return r;
            }else{
                return existing;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public MakeRecord read(int t) {
        MakeRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            r = create.selectFrom(MAKE).where(MAKE.ID.eq(t)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            create.delete(MAKE).where(MAKE.ID.eq(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(MakeRecord t) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(MAKE)
                    .set(MAKE.MAKE_, t.getMake())
                    .where(MAKE.ID.equal(t.getId()))
                    .execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
}
