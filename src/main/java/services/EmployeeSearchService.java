package services;

import static com.gemtastic.carshop.tables.Employees.EMPLOYEES;
import com.gemtastic.carshop.tables.records.EmployeesRecord;
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
public class EmployeeSearchService implements SearchServices<Result<EmployeesRecord>>{

    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    @Override
    public Result<EmployeesRecord> getAll() {
        Result<EmployeesRecord> employees = null;

        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            employees = create.fetch(EMPLOYEES);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Result<EmployeesRecord> getAllWhere(String selected, String constraint) {
        
        String column = selected.toLowerCase();
        Integer id = null;
        Result<EmployeesRecord> employee = null;
        
        try{
            id = Integer.parseInt(constraint);
        }catch(NumberFormatException e){}
        
            try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
                DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

                switch(column){
                    case "anställningsnr":
                        if(id != null){
                            employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.ID.eq(id)).fetch();
                        }
                        break;
                    case "telefon":
                        employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.PHONE.eq(constraint)).fetch();
                        break;
                    case "epost":
                        employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.EMAIL.eq(constraint)).fetch();
                        break;
                    case "username":
                        employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.USERNAME.eq(constraint)).fetch();
                        break;
                    case "password":
                        employee = create.selectFrom(EMPLOYEES).where(EMPLOYEES.PASSWORD.eq(constraint)).fetch();
                        break;
                    default:
                        System.out.println("Illegal search!");
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        return employee;
    }
}
