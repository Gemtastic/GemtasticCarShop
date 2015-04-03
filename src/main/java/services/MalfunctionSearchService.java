package services;

import static com.gemtastic.carshop.tables.MalfunctionReports.MALFUNCTION_REPORTS;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
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
public class MalfunctionSearchService implements SearchServices<Result<MalfunctionReportsRecord>>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<MalfunctionReportsRecord> getAll() {
        Result<MalfunctionReportsRecord> result = null;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            result = create.fetch(MALFUNCTION_REPORTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method only needs a constraint. {@code column} may be empty.
     * 
     * @param column
     * @param constraint
     * @return 
     */
    @Override
    public Result<MalfunctionReportsRecord> getAllWhere(String column, String constraint) {
        Result<MalfunctionReportsRecord> result = null;
        
        Integer id = null;
        
        try{
            id = Integer.parseInt(constraint);
        }catch(NumberFormatException e){}
        
        if(id != null){
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                result = create.selectFrom(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.CAR.eq(id)).fetch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
        return result;
    }
    
}
