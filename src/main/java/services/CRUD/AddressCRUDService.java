package services.CRUD;

import static com.gemtastic.carshop.tables.Address.ADDRESS;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
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
public class AddressCRUDService implements CRUDServices<AddressRecord>{
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    @Override
    public AddressRecord create(AddressRecord t) {
        AddressRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            r = create.newRecord(ADDRESS);
            System.out.println(r);
            
            r.setCity(t.getCity());
            r.setCo(t.getCo());
            r.setStreet(t.getStreet());
            r.setZip(t.getZip());
            r.store();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public AddressRecord read(int t) {
        AddressRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            r = create.selectFrom(ADDRESS).where(ADDRESS.ID.eq(t)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.delete(ADDRESS).where(ADDRESS.ID.eq(id)).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(AddressRecord address) {
        boolean success = false;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            create.update(ADDRESS)
                    .set(ADDRESS.CITY, address.getCity())
                    .set(ADDRESS.CO, address.getCo())
                    .set(ADDRESS.STREET, address.getStreet()) 
                    .set(ADDRESS.ZIP, address.getZip())
                    .where(ADDRESS.ID.equal(address.getId()))
                    .execute();
            success = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return success;
    }
    
}
