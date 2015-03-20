/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.gemtastic.carshop.tables.records.CustomerRecord;
import org.jooq.Result;
import services.CustomerSearchService;

/**
 *
 * @author Aizic Moisen
 */
public class TestingSearch {
    private static final String dbusername = "postgres";
    private static final String dbpassword = "g3mt45t1c";
    private static final String url = "jdbc:postgresql:postgres";
    
    public static void main(String[]args){
        
        CustomerSearchService service = new CustomerSearchService();
        
        Result<CustomerRecord> r = service.getAllWhere("firstname", "Herpina");
        
        System.out.println(r);
//        try (Connection connection = DriverManager.getConnection(url, dbusername, dbpassword)) {
//            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
//            
//            System.out.println(create.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(1)).fetch());
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        
        
    }
}
