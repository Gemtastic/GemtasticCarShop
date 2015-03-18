/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import application.DatabaseConnection;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import services.CustomerCRUDService;

/**
 *
 * @author Gemtastic
 */
public class TestingCRUD {
    
    public static void main(String[] args){
        CustomerCRUDService c = new CustomerCRUDService();
        
        
        
        CustomerRecord r = c.read(1);
        System.out.println(r);
    }
    
    
}
