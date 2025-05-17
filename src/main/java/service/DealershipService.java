package service;

import models.Dealership;
import models.Vehicle;
import persistence.DealershipFileManager;

import java.util.List;

public class DealershipService {
    private final Dealership dealership;

    public DealershipService(Dealership dealership) {
        this.dealership = dealership;
    }

    public List<Vehicle> getVehiclesByMakeOrModel(String option) {
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getMake().toLowerCase().contains(option)
                        || vehicle.getModel().toLowerCase().contains(option))
                .toList();
    }

    public List<Vehicle> getVehiclesByYearRange(int start, int end) {
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getYear() >= start && vehicle.getYear() <= end)
                .toList();
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color)).toList();
    }

    public List<Vehicle> getVehiclesByOdometerRange(double start, double end) {
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getOdometer() >= start && vehicle.getOdometer() <= end).toList();
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getType().equals(type)).toList();
    }

    public boolean addVehicle(Vehicle v) {
        if (isExists(v))
            return false; // VIN already exists, don't add duplicate

        dealership.addVehicle(v);
        new DealershipFileManager().saveDealership(dealership);
        return true;
    }

    private boolean isExists(Vehicle v) {
        return dealership.getVehicles().stream()
                .anyMatch(vehicle -> vehicle.getVin().equalsIgnoreCase(v.getVin()));
    }

    public boolean removeVehicle(String vin) {
        Vehicle target = dealership.getVehicles().stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst()
                .orElse(null);

        if (target != null) {
            dealership.removeVehicle(target);
            new DealershipFileManager().saveDealership(dealership);
            return true;
        } else {
            return false;
        }
    }


}
//    public Contract createContract(String type, Vehicle vehicle, String name, String email, boolean finance);
