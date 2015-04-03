package services;

import static com.gemtastic.carshop.tables.Certification.CERTIFICATION;
import com.gemtastic.carshop.tables.records.CertificationRecord;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.CRUD.EmployeeCRUDService;
import services.CRUD.MakeCRUDService;
import services.interfaces.SearchServices;

/**
 * This {@code SearchService} deviates a little from the others as it has 
 * utility methods for getting employees that are mechanics and lists of makes
 * serviceable by one mechanic and mechanics certified by a certain brand.
 * 
 * @author Gemtastic
 */
public class MechanicSearchService implements SearchServices<Result<CertificationRecord>>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<CertificationRecord> getAll() {
        Result<CertificationRecord> all = null;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            all = create.fetch(CERTIFICATION);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    /**
     * This method deviates from the other {@code getAllWhere()} in the sense 
     * that it retrieves all mechanics certified by a specific brand or all 
     * brands a mechanic is certified for.<p>
     * 
     * The {@code @param selected} must be either <i>mechanic</i> or <i>make</i>.
     * @param selected
     * @param constraint
     * @return 
     */
    @Override
    public Result<CertificationRecord> getAllWhere(String selected, String constraint) {
        Result<CertificationRecord> certifications = null;
        String column = selected.toLowerCase();

        Integer id = null;
        try{
            id = Integer.parseInt(constraint);
        }catch(NumberFormatException e){}
        
        if(id != null){
            if (column != null && constraint != null) {
                try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                    DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

                    switch(column){
                        case "mechanic":
                                certifications = create.selectFrom(CERTIFICATION).where(CERTIFICATION.EMPLOYEE.eq(id)).fetch();
                            break;
                        case "make":
                            certifications = create.selectFrom(CERTIFICATION).where(CERTIFICATION.MAKE.eq(id)).fetch();
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
        }

        return certifications;
    }
    
    public List<MakeRecord> getAllMakesOf(int mechanicId){
        List<MakeRecord> makes = FXCollections.observableArrayList();
        
        MakeCRUDService makeCRUD = new MakeCRUDService();
        
        String mechanic = String.valueOf(mechanicId);
        Result<CertificationRecord> all = getAllWhere("mechanic", mechanic);
        if(all != null && all.isNotEmpty()){
            for(CertificationRecord c : all){
                MakeRecord m = makeCRUD.read(c.getMake());
                    makes.add(m);
            }
        }
        
        return makes;
    }
    
    public List<EmployeesRecord> getAllMechanicsOf(int makeId){
        List<EmployeesRecord> employees = FXCollections.observableArrayList();
        
        EmployeeCRUDService employeeCRUD = new EmployeeCRUDService();
        
        String brand = String.valueOf(makeId);
        Result<CertificationRecord> all = getAllWhere("make", brand);
        
        for(CertificationRecord c : all){
            EmployeesRecord e = employeeCRUD.read(c.getEmployee());
            employees.add(e);
        }
        
        return employees;
    }
    
    public List<EmployeesRecord> getAllMechanics(){
        Result<CertificationRecord> all = getAll();
        List<EmployeesRecord> mechanics = FXCollections.observableArrayList();
        
        EmployeeCRUDService service = new EmployeeCRUDService();
        
        for(CertificationRecord c : all){
            mechanics.add(service.read(c.getEmployee()));
        }
        return mechanics;
    }
    
    public CertificationRecord create(CertificationRecord t) {
        CertificationRecord newCert = null;
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            create.insertInto(CERTIFICATION).set(t).execute();
            newCert = t;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newCert;
    }
    
    public void delete(CertificationRecord c) {
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            create.delete(CERTIFICATION)
                    .where(CERTIFICATION.EMPLOYEE.eq(c.getEmployee()).and(CERTIFICATION.MAKE.eq(c.getMake())))
                    .execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public CertificationRecord getRecord(EmployeesRecord mechanic, MakeRecord make){
        CertificationRecord c = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            c = create.selectFrom(CERTIFICATION)
                        .where(CERTIFICATION.EMPLOYEE.eq(mechanic.getId())
                        .and(CERTIFICATION.MAKE.eq(make.getId())))
                        .fetchOne();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
