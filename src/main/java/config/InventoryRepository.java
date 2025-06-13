package config;

import models.Vehicle;

import java.util.List;

public interface InventoryRepository {
    List<Vehicle> getAll();
    List<Vehicle> getByModel(String model);
    List<Vehicle> getByYear(int year);
    List<Vehicle> getByColor(String color);
    List<Vehicle> getByMileageRange(int minimumMileage, int maximumMileage);
    List<Vehicle> getByType(String type);
    void add(Vehicle vehicle);
    void update(String vin, Vehicle vehicle);
    void delete(String vin);
}
