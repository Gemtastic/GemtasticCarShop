package services;

import services.CRUD.CarCRUDService;
import static com.gemtastic.carshop.tables.Car.CAR;
import static com.gemtastic.carshop.tables.Ownership.OWNERSHIP;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.OwnershipRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.CRUD.CustomerCRUDService;
import services.interfaces.SearchServices;

/**
 *
 * @author Aizic Moisen
 */
public class CarSearchService implements SearchServices {

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<CarRecord> getAll() {
        Result<CarRecord> car = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            car = create.fetch(CAR);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public Result<CarRecord> getAllWhere(String column, String constraint) {
        Result<CarRecord> car = null;
        String selectColumn = column.toLowerCase();

        if (selectColumn != null && constraint != null) {
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
                
                switch(selectColumn){
                    case "id":
                        int id = Integer.parseInt(constraint);
                        car = create.selectFrom(CAR).where(CAR.ID.eq(id)).fetch();
                        break;
                    case "registreringsskylt":
                        car = create.selectFrom(CAR).where(CAR.LICENSE_PLATE.eq(constraint)).fetch();
                        break;
                    case "märke":
                        int modelMake = Integer.parseInt(constraint);
                        car = create.selectFrom(CAR).where(CAR.CAR_MODEL.eq(modelMake)).fetch();
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

        return car;
    }
    
    
    public List<CarRecord> getAllCarsByOwner(int ownerId){
        List<CarRecord> cars = new ArrayList<>();
        CarCRUDService service = new CarCRUDService();
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Result<OwnershipRecord> id = create.selectFrom(OWNERSHIP).where(OWNERSHIP.OWNER.eq(ownerId)).fetch();
            
            for (OwnershipRecord r : id) {
                CarRecord car = service.read(r.getCar());
                try{
                    cars.add(car);
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    
    public List<CustomerRecord> getOwnersByCar(int carId){
        List<CustomerRecord> owners = FXCollections.observableArrayList();
        CustomerCRUDService service = new CustomerCRUDService();
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Result<OwnershipRecord> id = create.selectFrom(OWNERSHIP).where(OWNERSHIP.CAR.eq(carId)).fetch();
            
            for (OwnershipRecord r : id) {
                CustomerRecord car = service.read(r.getOwner());
                try{
                    owners.add(car);
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return owners;
    }
}
