package services.CRUD;

import static com.gemtastic.carshop.tables.MalfunctionReports.MALFUNCTION_REPORTS;
import com.gemtastic.carshop.tables.records.MalfunctionReportsRecord;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class MalfunctionCRUDService implements CRUDServices<MalfunctionReportsRecord>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public MalfunctionReportsRecord create(MalfunctionReportsRecord t) {
        MalfunctionReportsRecord m = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            m = create.newRecord(MALFUNCTION_REPORTS);
            m.setCar(t.getCar());
            m.setMessage(t.getMessage());
            m.setReportDate(Date.valueOf(LocalDate.now()));
            m.store();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public MalfunctionReportsRecord read(int t) {
        MalfunctionReportsRecord m = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            m = create.selectFrom(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.ID.eq(t)).fetchOne();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return m;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.delete(MALFUNCTION_REPORTS).where(MALFUNCTION_REPORTS.ID.eq(id)).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(MalfunctionReportsRecord t) {
        boolean success = false;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(MALFUNCTION_REPORTS)
                    .set(MALFUNCTION_REPORTS.CAR, t.getCar())
                    .set(MALFUNCTION_REPORTS.MESSAGE, t.getMessage())
                    .set(MALFUNCTION_REPORTS.REPORT_DATE, t.getReportDate())
                    .where(MALFUNCTION_REPORTS.ID.equal(t.getId()))
                    .execute();
            
            success = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return success;
    }
    
}
