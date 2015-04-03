package services.CRUD;

import static com.gemtastic.carshop.tables.Car.CAR;
import static com.gemtastic.carshop.tables.Make.MAKE;
import com.gemtastic.carshop.tables.records.CarRecord;
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
public class CarCRUDService implements CRUDServices<CarRecord>{
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";

    @Override
    public CarRecord create(CarRecord t) {
        CarRecord car = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            CarRecord exists = create.selectFrom(CAR).where(CAR.LICENSE_PLATE.eq(t.getLicensePlate())).fetchOne();
            if(exists != null){
                return exists;
            }else{
                car = create.newRecord(CAR);
                car.setLicensePlate(t.getLicensePlate());
                car.setOdometer(t.getOdometer());
                car.setCarModel(t.getCarModel());
                car.store();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public CarRecord read(int t) {
        CarRecord car = null;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            car = create.selectFrom(CAR).where(CAR.ID.eq(t)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            create.delete(CAR).where(CAR.ID.eq(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(CarRecord t) {
        boolean success = false;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(CAR)
                    .set(CAR.CAR_MODEL, t.getCarModel())
                    .set(CAR.LICENSE_PLATE, t.getLicensePlate())
                    .set(CAR.ODOMETER, t.getOdometer())
                    .where(CAR.ID.equal(t.getId()))
                    .execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    
}
