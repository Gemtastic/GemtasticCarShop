/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import application.CachedPostgresContextProvider;
import application.DatabaseContextProvider;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import services.AddressCRUDService;
import services.CustomerCRUDService;

import java.sql.SQLException;

/**
 *
 * @author Gemtastic
 */
public class TestingCRUD {
    
    public static void main(String[] args) throws SQLException {
        final DatabaseContextProvider provider = new CachedPostgresContextProvider();
        DSLContext create = provider.getDslContext("jdbc:postgres:postgres", "postgres", "g3mt45t1c");
        CustomerCRUDService c = new CustomerCRUDService(create);
        AddressCRUDService a = new AddressCRUDService(create);
        
        
        CustomerRecord cr = c.read(4);
        AddressRecord ar = a.read(cr.getAddress());
        System.out.println(cr + "\n" + ar);
    }
    
    
}
