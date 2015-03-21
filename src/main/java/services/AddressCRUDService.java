package services;

import static com.gemtastic.carshop.tables.Address.ADDRESS;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;

import com.gemtastic.carshop.tables.Address;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Aizic Moisen
 */
public class AddressCRUDService implements CRUDServices<AddressRecord>{

    private DSLContext create;

    public AddressCRUDService(DSLContext create) {
        this.create = create;
    }

    @Override
    public boolean create(AddressRecord t) {
        boolean success = false;
        
        return success;
    }

    @Override
    public AddressRecord read(int t) {
        AddressRecord r = null;
        r = create.selectFrom(ADDRESS).where(ADDRESS.ID.eq(t)).fetchOne();
        return r;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AddressRecord address) {
        create.update(ADDRESS)
                .set(ADDRESS.CITY, address.getCity())
                .set(ADDRESS.CO, address.getCo())
                .set(ADDRESS.STREET, address.getStreet())
                .set(ADDRESS.ZIP, address.getZip())
                .where(ADDRESS.ID.equal(address.getId()))
                .execute();
        return true;
    }
    
}
