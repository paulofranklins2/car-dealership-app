package service;

import models.Dealership;
import models.Vehicle;

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

    public List<Vehicle> getVehiclesByYearRange(int start, int end){
        return dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getYear() >= start && vehicle.getYear() <= end)
                .toList();
    }
//
//    public boolean addVehicle(Vehicle v);
//
//    public boolean removeVehicle(String vin);
//
//    public Contract createContract(String type, Vehicle vehicle, String name, String email, boolean finance);

}
