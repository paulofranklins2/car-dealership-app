package models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Dealership {
    private final String name;
    private final String address;
    private final String phone;
    private final List<Vehicle> vehicles = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void removeVehicle(Vehicle v) {
        vehicles.remove(v);
    }

    @Override
    public String toString() {
        return name + " | " + address + " | " + phone;
    }
}
