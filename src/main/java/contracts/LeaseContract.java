package contracts;

import models.Vehicle;

public class LeaseContract extends Contract {

    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double expectedEndingValue = getVehicle().getPrice() * 0.5;
        double leaseFee = getVehicle().getPrice() * 0.07;
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return (getTotalPrice() * (1 + 0.04)) / 36;
    }
}
