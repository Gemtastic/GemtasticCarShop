package AlternativeRecords;

import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;

/**
 *
 * @author Gemtastic
 */
public class AppointmentAppointmentsRecord {
    private int id;
    private int mechanic;
    private String date;
    private String car;
    private String customer;
    private String type;
    
    public AppointmentAppointmentsRecord(AppointmentsRecord appointment, CustomerRecord customer, CarRecord car){
        id = appointment.getId();
        mechanic = appointment.getMechanic();
        date = appointment.getPerformedDate().toString();
        this.car = car.getLicensePlate();
        this.customer = customer.getId() + ", " + customer.getFirstName() + " " + customer.getLastName();
        type = appointment.getType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMechanic() {
        return mechanic;
    }

    public void setMechanic(int mechanic) {
        this.mechanic = mechanic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
