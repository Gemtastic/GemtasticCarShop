package AlternativeRecords;

import com.gemtastic.carshop.tables.records.AppointmentsRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;

/**
 *
 * @author Gemtastic
 */
public class AppointmentBookingRecord {
    private int mechanic;
    private int bookingId;
    private String customer;
    private String vehicle;
    private String date;
    private String type;
    
    public AppointmentBookingRecord(AppointmentsRecord appointment, CarRecord car, CustomerRecord customer){
        mechanic = appointment.getMechanic();
        bookingId = appointment.getId();
        this.customer =  customer.getId() + ", " + customer.getFirstName() + " " + customer.getLastName();
        vehicle = car.getLicensePlate();
        date = appointment.getScheduledDate().toString();
        type = appointment.getType();
    }

    public int getMechanic() {
        return mechanic;
    }

    public void setMechanic(int mechanic) {
        this.mechanic = mechanic;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
