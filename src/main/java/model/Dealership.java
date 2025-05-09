package model;

import lombok.Getter;
import lombok.Setter;
import service.DealershipFileManager;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private DealershipFileManager dealershipFileManager;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {

    }

    public void removeVehicle(String vin) {

    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public ArrayList<Vehicle> getVehicleByPrice(BigDecimal price) {

        return null;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.address + " | " + this.phone;
    }
}
