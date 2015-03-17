/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author Gemtastic
 */
public class TestingConnection {
    public static void main(String[] args){
        
        ObservableList<CustomerRecord> l = FXCollections.observableArrayList();
        
        try(Connection con = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "g3mt45t1c")){
            DSLContext jooq = DSL.using(con, SQLDialect.POSTGRES);
            
//            CustomerRecord r = jooq.fetch(CUSTOMER);
            for(CustomerRecord r : jooq.fetch(CUSTOMER)){
                System.out.println(r + "record");
                l.add(r);
            }
            
            System.out.println();
            
            Record rr = (Record) jooq.fetch(CUSTOMER);

            System.out.println(rr);
            
//            System.out.println();
//            
//            System.out.println(l.toString());
//            
//            System.out.println(l.get(1).getFirstName());
            
//            System.out.println(r);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
