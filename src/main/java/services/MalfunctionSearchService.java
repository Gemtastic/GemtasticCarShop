package services;

import static com.gemtastic.carshop.tables.MalfunctionReports.MALFUNCTION_REPORTS;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.sql.Connection;
import java.sql.Date;
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
     * This method accepts "nummerplåt" and "datum" in the {@code column}.
     * 
     * @param column
     * @param constraint
     * @return 
     */
    @Override
    public Result<MalfunctionReportsRecord> getAllWhere(String column, String constraint) {
        CarSearchService service = new CarSearchService();
        Result<MalfunctionReportsRecord> result = null;
        Integer id = null;
        
        try{
            id = Integer.parseInt(constraint);
        }catch(NumberFormatException e){}
        
        String search = column.toLowerCase();
        
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                
                switch(search){
                    case "nummerplåt":
                        CarRecord r = service.getByPlate(constraint);
                        if(r != null){
                            result = create.selectFrom(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.CAR.eq(r.getId())).fetch();
                        }
                        break;
                    case "datum":
                        Date date = null;
                        try{
                            date = Date.valueOf(constraint);
                        }catch(Exception e){}
                        if(date != null){
                            result = create.selectFrom(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.REPORT_DATE.eq(date)).fetch();
                        }
                        break;
                    case "car":
                        if(id != null){
                            result = create.selectFrom(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.CAR.eq(id)).fetch();
                        }
                        break;
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        return result;
    }
    
}
