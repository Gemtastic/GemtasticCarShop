/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.SearchServices;

/**
 *
 * @author Aizic Moisen
 */
public class CustomerSearchService implements SearchServices {


    private final DSLContext create;

    public CustomerSearchService(DSLContext create) {
        this.create = create;
    }

    @Override
    public Result<CustomerRecord> getAll() {
        return create.fetch(CUSTOMER);
    }

    @Override
    public Result<CustomerRecord> getAllWhere(String selected, String constraint) {
        Result<CustomerRecord> customers = null;
        String column = selected.toLowerCase();

        if (column != null && constraint != null) {
            switch(column){
                case "namn":
                    customers = create.selectFrom(CUSTOMER).where(CUSTOMER.FIRST_NAME.eq(constraint)).fetch();
                    break;
                case "efternamn":
                    customers = create.selectFrom(CUSTOMER).where(CUSTOMER.LAST_NAME.eq(constraint)).fetch();
                    break;
                case "email":
                    customers = create.selectFrom(CUSTOMER).where(CUSTOMER.EMAIL.eq(constraint)).fetch();
                    break;
                case "telefon":
                    customers = create.selectFrom(CUSTOMER).where(CUSTOMER.PHONE.eq(constraint)).fetch();
                    break;
                case "kundnr":
                    int id = Integer.parseInt(constraint);
                    customers = create.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(id)).fetch();
                    break;
                default:
                    System.out.println("Something borked with the column!");
            }
        }else{
            System.out.println("Invalid search!");
        }

        return customers;
    }

}
