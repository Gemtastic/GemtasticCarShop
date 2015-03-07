package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gemtastic on 2015-03-05.
 */
public class Car {
    private String licensePlate;
    private String brand;
    private String fuelType;
    private String model;
    private int year;
    private int id;
    private int customerID;
    private long meterposition;

    private List<Customer> owners;

    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeterposition(long meterposition) {
        this.meterposition = meterposition;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOwners(List<Customer> owners) {
        this.owners = owners;
    }

    public long getMeterposition() {
        return meterposition;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public List<Customer> getOwners() {
        return owners;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
