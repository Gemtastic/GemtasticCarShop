package services;

import static com.gemtastic.carshop.tables.Make.MAKE;
import com.gemtastic.carshop.tables.records.MakeRecord;
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
public class MakeSearchService implements SearchServices<Result<MakeRecord>>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<MakeRecord> getAll() {
        Result<MakeRecord> make = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            make = create.fetch(MAKE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return make;
    }

    /**
     * This method always return null.
     * 
     * @param column
     * @param constraint
     * @return 
     */
    @Override
    public Result<MakeRecord> getAllWhere(String column, String constraint) {
        Result<MakeRecord> make = null;
        
        
        return make;
    }
    
}
