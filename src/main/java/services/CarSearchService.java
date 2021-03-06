package services;

import services.CRUD.CarCRUDService;
import static com.gemtastic.carshop.tables.Car.CAR;
import static com.gemtastic.carshop.tables.CarModel.CAR_MODEL;
import static com.gemtastic.carshop.tables.Make.MAKE;
import static com.gemtastic.carshop.tables.Ownership.OWNERSHIP;
import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
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

        Integer id = null;

        try {
            id = Integer.parseInt(constraint);
        } catch (NumberFormatException e) {
        }

        if (selectColumn != null && constraint != null) {
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

                car = create.newResult(CAR);

                switch (selectColumn) {
                    case "id":
                        if(id != null){
                        car = create.selectFrom(CAR).where(CAR.ID.eq(id)).fetch();
                        }
                        break;
                    case "kundnr":
                        if(id != null){
                        List<CarRecord> cars = getAllCarsByOwner(id);
                        car.addAll(cars);
                        }
                        break;
                    case "nummerplåt":
                        car = create.selectFrom(CAR).where(CAR.LICENSE_PLATE.eq(constraint)).fetch();
                        break;
                    case "märke":
                        MakeRecord make = create.selectFrom(MAKE).where(MAKE.MAKE_.eq(constraint)).fetchOne();
                        if (make != null) {
                            Result<CarModelRecord> model = create.selectFrom(CAR_MODEL).where(CAR_MODEL.MAKE.eq(make.getId())).fetch();
                            if (model != null && model.isNotEmpty()) {
                                for (CarModelRecord r : model) {
                                    car.addAll(create.selectFrom(CAR).where(CAR.CAR_MODEL.eq(r.getId())).fetch());
                                }
                            }
                        }
                        break;
                    case "modell":
                        CarModelRecord m = create.selectFrom(CAR_MODEL).where(CAR_MODEL.MODEL.eq(constraint)).fetchOne();
                        if (m != null){
                            car = create.selectFrom(CAR).where(CAR.CAR_MODEL.eq(m.getId())).fetch();
                        }
                        break;
                    default:
                        System.out.println("Something borked with the column!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid search!");
        }

        return car;
    }

    public List<CarRecord> getAllCarsByOwner(int ownerId) {
        List<CarRecord> cars = new ArrayList<>();
        CarCRUDService service = new CarCRUDService();

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Result<OwnershipRecord> id = create.selectFrom(OWNERSHIP).where(OWNERSHIP.OWNER.eq(ownerId)).fetch();

            for (OwnershipRecord r : id) {
                CarRecord car = service.read(r.getCar());
                try {
                    cars.add(car);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<CustomerRecord> getOwnersByCar(int carId) {
        List<CustomerRecord> owners = FXCollections.observableArrayList();
        CustomerCRUDService service = new CustomerCRUDService();

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            Result<OwnershipRecord> id = create.selectFrom(OWNERSHIP).where(OWNERSHIP.CAR.eq(carId)).fetch();

            for (OwnershipRecord r : id) {
                CustomerRecord car = service.read(r.getOwner());
                try {
                    owners.add(car);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owners;
    }

    public CarRecord getByPlate(String plate) {
        CarRecord car = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            car = create.selectFrom(CAR).where(CAR.LICENSE_PLATE.eq(plate)).fetchOne();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }
}
