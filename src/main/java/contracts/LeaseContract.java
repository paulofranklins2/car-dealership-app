package contracts;

import models.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal leasingFee = new BigDecimal("0.07");
        BigDecimal expectedEndingValue = getVehicle().getPrice().multiply(new BigDecimal("0.50"));
        BigDecimal leaseFee = getVehicle().getPrice().multiply(leasingFee);
        return expectedEndingValue.add(leaseFee);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        BigDecimal financingFee = new BigDecimal("0.04");
        BigDecimal longTerm = new BigDecimal(36);
//        return (getTotalPrice() * (1 + financingFee)) / longTerm;
        return (getTotalPrice().multiply(new BigDecimal(1).add(financingFee))).divide(longTerm, 2, RoundingMode.HALF_UP);
    }
}
