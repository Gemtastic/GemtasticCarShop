package services;

import static com.gemtastic.carshop.tables.Address.ADDRESS;
import static com.gemtastic.carshop.tables.Customer.CUSTOMER;
import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import services.interfaces.CRUDServices;

/**
 *
 * @author Gemtastic
 */
public class CustomerCRUDService implements CRUDServices<CustomerRecord> {

    private DSLContext create;

    public CustomerCRUDService(DSLContext create) {
        this.create = create;
    }

    @Override
    public boolean create(CustomerRecord customer) {
        boolean success = false;

        CustomerRecord r = create.newRecord(CUSTOMER);
        r.setAddress(customer.getAddress());
        r.setDateOfBirth(customer.getDateOfBirth());
        r.setEmail(customer.getEmail());
        r.setFirstName(customer.getFirstName());
        r.setGender(customer.getGender());
        r.setLastName(customer.getLastName());
        r.setPhone(customer.getPhone());
        r.store();

        // Get the (possibly) auto-generated ID from the record
        Integer id = r.getId();
        System.out.println("ID = " + id);
        r.setId(id);
        r.store();

        System.out.println(id);
        if(id != null){
            success = true;
        }

        return success;
    }

    @Override
    public CustomerRecord read(int customer) {
        return create.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(customer)).fetchOne();
    }

    @Override
    public void delete(int id) {
        create.delete(CUSTOMER).where(CUSTOMER.ID.eq(id)).execute();
    }

    public boolean update(CustomerRecord customerUpdate) {
        create.update(CUSTOMER)
                .set(CUSTOMER.FIRST_NAME, customerUpdate.getFirstName())
                .set(CUSTOMER.LAST_NAME, customerUpdate.getLastName())
                .set(CUSTOMER.GENDER, customerUpdate.getGender())
                .set(CUSTOMER.EMAIL, customerUpdate.getEmail())
                .set(CUSTOMER.DATE_OF_BIRTH, customerUpdate.getDateOfBirth())
                .set(CUSTOMER.PHONE, customerUpdate.getPhone())
                .where(CUSTOMER.ID.equal(customerUpdate.getId()))
                .execute();
        return true;
    }

    public Result<CustomerRecord> getAllCustomers() {
        return create.selectFrom(CUSTOMER).getResult();
    }
}
