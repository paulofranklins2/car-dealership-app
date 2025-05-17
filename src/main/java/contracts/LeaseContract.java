package contracts;

import models.Vehicle;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double leasingFee = 0.07;
        double expectedEndingValue = getVehicle().getPrice() * 0.5;
        double leaseFee = getVehicle().getPrice() * leasingFee;
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double financingFee = 0.04;
        int longTerm = 36;
        return (getTotalPrice() * (1 + financingFee)) / longTerm;
    }
}
