package services.CRUD;

import static com.gemtastic.carshop.tables.CarModel.CAR_MODEL;
import com.gemtastic.carshop.tables.records.CarModelRecord;
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
public class ModelCRUDService implements CRUDServices<CarModelRecord>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public CarModelRecord create(CarModelRecord t) {
        CarModelRecord r = null;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            CarModelRecord exist = create.selectFrom(CAR_MODEL)
                                         .where(CAR_MODEL.MAKE.equal(t.getMake()), 
                                                CAR_MODEL.FUEL_TYPE.equal(t.getFuelType()),
                                                CAR_MODEL.MAKE.equal(t.getMake()),
                                                CAR_MODEL.MODEL_YEAR.equal(t.getModelYear()))
                                         .fetchOne();
            if(exist == null){
                r = create.newRecord(CAR_MODEL);
                r.setFuelType(t.getFuelType());
                r.setMake(t.getMake());
                r.setModel(t.getModel());
                r.setModelYear(t.getModelYear());
                r.store();
                
                return r;
            }else{
                return exist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public CarModelRecord read(int t) {
        CarModelRecord r = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            r = create.selectFrom(CAR_MODEL).where(CAR_MODEL.ID.eq(t)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return r;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            create.selectFrom(CAR_MODEL).where(CAR_MODEL.ID.eq(id)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(CarModelRecord t) {
        boolean success = false;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

            create.update(CAR_MODEL)
                    .set(CAR_MODEL.FUEL_TYPE, t.getFuelType())
                    .set(CAR_MODEL.MAKE, t.getMake())
                    .set(CAR_MODEL.MODEL, t.getModel())
                    .set(CAR_MODEL.MODEL_YEAR, t.getModelYear())
                    .where(CAR_MODEL.ID.equal(t.getId()))
                    .execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return success;
    }
    
}
