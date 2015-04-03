package services;

import static com.gemtastic.carshop.tables.Appointments.APPOINTMENTS;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author Gemtastic
 */
public class StatisticsService {
    
    private final String dbusername = "postgres";
    private final String dbpassword = "g3mt45t1c";
    private final String url = "jdbc:postgresql:postgres";
    
    public Integer getAmountByGender(char g){
        Result<CustomerRecord> result = null;
        Integer amount = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            switch(g){
                case 'M':
                    result = create.selectFrom(CUSTOMER).where(CUSTOMER.GENDER.eq("M")).fetch();
                    amount = result.size();
                    break;
                case 'F':
                    result = create.selectFrom(CUSTOMER).where(CUSTOMER.GENDER.eq("F")).fetch();
                    amount = result.size();
                    break;
                default:
                    amount = 0;
                    System.out.println("Invalid search!");
                    break;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return amount;
    }
    
    public int getAppointmentsByGender(char g){
        Result<CustomerRecord> result = null;
        int amount = 0;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            switch(g){
                case 'M':
                    result = create.selectFrom(CUSTOMER).where(CUSTOMER.GENDER.eq("M")).fetch();
                    
                    for(CustomerRecord r : result){
                        amount++;
                    }
                    break;
                case 'F':
                    result = create.selectFrom(CUSTOMER).where(CUSTOMER.GENDER.eq("F")).fetch();
                    
                    for(CustomerRecord r : result){
                        amount++;
                    }
                    break;
                default:
                    System.out.println("Invalid search!");
                    break;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return amount;
    }
    
    /**
     * Accepts y for youngest and o for oldest.
     * @param constraint
     * @return 
     */
    public Integer getAge(String constraint){
        Result<CustomerRecord> result = null;
        int today = LocalDate.now().getYear();
        int birth = 0;
        Integer age = null;
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            result = create.fetch(CUSTOMER);
            
            switch(constraint){
                case "y":
                    CustomerRecord y = result.sortDesc(CUSTOMER.DATE_OF_BIRTH).get(0);
                    birth = y.getDateOfBirth().toLocalDate().getYear();
                    age = today - birth;
                    break;
                case "o":
                    CustomerRecord o = result.sortAsc(CUSTOMER.DATE_OF_BIRTH).get(0);
                    birth = o.getDateOfBirth().toLocalDate().getYear();
                    age = today - birth;
                    break;
                default:
                    System.out.println("Invalid search!");
                    age = 0;
                    break;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return age;
    }
    
    public int getAppointmentsLastYear(){
        Result<AppointmentsRecord> result = null;
        int amount = 0;
        
        int thisYear = LocalDate.now().getYear();
        int lastYear = thisYear - 1;
        
        LocalDateTime first = LocalDateTime.of(lastYear, 
                                                1, 
                                                1,
                                                0, 
                                                1);
        LocalDateTime last = LocalDateTime.of(lastYear,
                                                12, 
                                                31,
                                                23, 
                                                59);

        Timestamp beginning = Timestamp.valueOf(first);
        Timestamp end = Timestamp.valueOf(last);
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            result = create.selectFrom(APPOINTMENTS)
                            .where(APPOINTMENTS.PERFORMED_DATE.ge(beginning)
                                .and(APPOINTMENTS.PERFORMED_DATE.le(end)))
                            .fetch();
            amount = result.size();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return amount;
    }
    
    public double getAverageAppThisYear(){
        Result<AppointmentsRecord> result = null;
        double amount = 0.0;
        
        LocalDateTime first = LocalDateTime.of(LocalDateTime.now().getYear(), 
                                                1, 
                                                1,
                                                0, 
                                                1);

        Timestamp beginning = Timestamp.valueOf(first);
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            result = create.selectFrom(APPOINTMENTS)
                            .where(APPOINTMENTS.PERFORMED_DATE.ge(beginning)
                                    .and(APPOINTMENTS.PERFORMED_DATE.le(Timestamp.valueOf(LocalDateTime.now()))))
                            .fetch();
            
            int total = result.size();
            float months = LocalDate.now().getMonth().getValue();
            if(total != 0){
                amount = total/months;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return amount;
    }
    
    public double getAverageAppLastYear(){
        double average = 0.0;
        
        int appointments = getAppointmentsLastYear();
        float months = 12;
        
        if(appointments != 0){
            average = appointments/months;
        }
        
        return average;
    }
    
    public double getAverageAppThisMonth(){
        Result<AppointmentsRecord> result = null;
        double amount = 0.0;
        
        LocalDateTime first = LocalDateTime.of(LocalDateTime.now().getYear(), 
                                                LocalDateTime.now().getMonth(), 
                                                1,
                                                0, 
                                                1);

        Timestamp beginning = Timestamp.valueOf(first);
        
        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            
            result = create.selectFrom(APPOINTMENTS)
                            .where(APPOINTMENTS.PERFORMED_DATE.ge(beginning)
                                    .and(APPOINTMENTS.PERFORMED_DATE.le(Timestamp.valueOf(LocalDateTime.now()))))
                            .fetch();
            
            int app = result.size();
            float days = LocalDate.now().getDayOfMonth();
            
            if(app != 0){
                amount = app/days;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return amount;
    }
}
