package services;

import static com.gemtastic.carshop.tables.Address.ADDRESS;
import com.gemtastic.carshop.tables.records.AddressRecord;
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
 * @author Aizic Moisen
 */
public class AddressSearchService implements SearchServices{
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    
    @Override
    public Result<AddressRecord> getAll() {
        
        Result<AddressRecord> addresses = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            addresses = create.fetch(ADDRESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
        
    }

    /**
     * 
     * @param column
     * @param searchVar
     * @return 
     */
    @Override
    public AddressRecord getAllWhere(String column, String searchVar) {
        AddressRecord address = null;
        
        Integer customerId = null;
        
        try{
            customerId = Integer.parseInt(searchVar);
        }catch(NullPointerException e){}

            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                
                if(customerId != null){
                    address = create.selectFrom(ADDRESS).where(ADDRESS.ID.eq(customerId)).fetchOne();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return address;
    }
    
}
