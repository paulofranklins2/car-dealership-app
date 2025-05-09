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
        //public String saveVehicle(String vin, int year, String make, String model, String type, String color, double odometer, BigDecimal price)
        inventory.add(vehicle);
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealershipOverWrite(this);
        System.out.println("Vehicle added successfully");
    }

    public void removeVehicle(String vin) {
        if (inventory.removeIf(vehicle -> vehicle.getVin().equalsIgnoreCase(vin))) {
            DealershipFileManager dealershipFileManager = new DealershipFileManager();
            dealershipFileManager.saveDealershipOverWrite(this);
            System.out.println("Vehicle removed successfully");
        } else {
            System.out.println("Vehicle not found");
        }
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public ArrayList<Vehicle> getVehicleByPrice(BigDecimal price) {
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice().compareTo(price) == 0) {
                result.add(vehicle);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.address + " | " + this.phone;
    }
}
