package Controller.appointments;

import static com.gemtastic.carshop.tables.Appointments.APPOINTMENTS;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.SearchServices;

/**
 *
 * @author Gemtastic
 */
public class AppointmentSearchService implements SearchServices<Result<AppointmentsRecord>> {

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<AppointmentsRecord> getAll() {
         Result<AppointmentsRecord> appointments = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            appointments = create.fetch(APPOINTMENTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    @Override
    public Result<AppointmentsRecord> getAllWhere(String selected, String constraint) {
        Result<AppointmentsRecord> appointment = null;
        String column = selected.toLowerCase();
        int carId = Integer.parseInt(constraint);

        if (column != null && constraint != null) {
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                
                switch(column){
                    case "kundnr":
                        appointment = create.selectFrom(APPOINTMENTS).where(APPOINTMENTS.COMMISSIONER.eq(carId)).fetch();
                        break;
                    case "nummerplåt":
                        appointment = create.selectFrom(APPOINTMENTS).where(APPOINTMENTS.CAR.eq(carId)).fetch();
                        break;
                    case "booking":
                        LocalDate getDate = LocalDate.now();
                        Date today = Date.valueOf(getDate);
                        appointment = create.selectFrom(APPOINTMENTS).where(APPOINTMENTS.SCHEDULED_DATE.ge(DSL.currentTimestamp())).fetch();
                        break;
                    default:
                        System.out.println("Something borked with the column!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Invalid search!");
        }

        return appointment;
    }
    
}
