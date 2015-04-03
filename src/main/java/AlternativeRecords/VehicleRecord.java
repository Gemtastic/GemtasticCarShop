package AlternativeRecords;

import com.gemtastic.carshop.tables.records.CarModelRecord;
import com.gemtastic.carshop.tables.records.CarRecord;
import com.gemtastic.carshop.tables.records.MakeRecord;

/**
 *
 * @author Gemtastic
 */
public class VehicleRecord {
    private int id;
    private int year;
    private long odometer;
    private String plates;
    private String model;
    private String make;
    private String fuelType;
    
    public VehicleRecord(CarRecord car, CarModelRecord model, MakeRecord make){
        id = car.getId();
        year = model.getModelYear();
        odometer = car.getOdometer();
        plates = car.getLicensePlate();
        this.model = model.getModel();
        this.make = make.getMake();
        fuelType = model.getFuelType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getOdometer() {
        return odometer;
    }

    public void setOdometer(long odometer) {
        this.odometer = odometer;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    
}
