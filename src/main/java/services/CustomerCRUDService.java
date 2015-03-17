package services;

import application.DatabaseConnection;
import com.gemtastic.carshop.tables.Customer;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class CustomerCRUDService implements CRUDServices{
    
    public ObservableList<Customer> customers;
    
    public ObservableList<Customer> getAll(){
        customers = FXCollections.observableArrayList();
        
        
        DatabaseConnection con = new DatabaseConnection();
        
        DSLContext create = con.connect();
        
        CustomerRecord r = create.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(1)).fetchOne();
        
        
        return customers;
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer read(String customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
