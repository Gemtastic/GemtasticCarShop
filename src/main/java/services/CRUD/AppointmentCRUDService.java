package services.CRUD;

import static com.gemtastic.carshop.tables.Appointments.APPOINTMENTS;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class AppointmentCRUDService implements CRUDServices<AppointmentsRecord>{
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    @Override
    public AppointmentsRecord create(AppointmentsRecord appointment) {
        AppointmentsRecord r = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            r = create.newRecord(APPOINTMENTS);
            r.setCar(appointment.getCar());
            r.setComments(appointment.getComments());
            r.setCommissioner(appointment.getCommissioner());
            r.setMechanic(appointment.getMechanic());
            r.setPerformedDate(appointment.getPerformedDate());
            r.setScheduledDate(appointment.getScheduledDate());
            r.setType(appointment.getType());
            r.store();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public AppointmentsRecord read(int id) {
        AppointmentsRecord a = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            a = create.selectFrom(APPOINTMENTS).where(APPOINTMENTS.ID.eq(id)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.delete(APPOINTMENTS).where(APPOINTMENTS.ID.eq(id)).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(AppointmentsRecord appointment) {
        boolean success = false;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(APPOINTMENTS)
                    .set(APPOINTMENTS.CAR, appointment.getCar())
                    .set(APPOINTMENTS.COMMENTS, appointment.getComments())
                    .set(APPOINTMENTS.COMMISSIONER, appointment.getCommissioner())
                    .set(APPOINTMENTS.MECHANIC, appointment.getMechanic())
                    .set(APPOINTMENTS.PERFORMED_DATE, appointment.getPerformedDate())
                    .set(APPOINTMENTS.SCHEDULED_DATE, appointment.getScheduledDate())
                    .set(APPOINTMENTS.TYPE, appointment.getType())
                    .where(APPOINTMENTS.ID.equal(appointment.getId()))
                    .execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
}
