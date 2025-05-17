package contracts;


import lombok.Getter;
import models.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class SalesContract extends Contract {
    private final boolean finance;
    private final BigDecimal salesTax = new BigDecimal("0.05");
    private final BigDecimal recordingFee = new BigDecimal("100");
    private final BigDecimal processingFee = getVehicle().getPrice().compareTo(new BigDecimal("10000")) <= 0 ? new BigDecimal("295") : new BigDecimal("495");
    private final BigDecimal financeRate = getTotalPrice().compareTo(new BigDecimal(10000)) >= 0 ? new BigDecimal("0.0425") : new BigDecimal("0.0525");

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean finance) {
        super(date, name, email, vehicle);
        this.finance = finance;
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal tax = getVehicle().getPrice().multiply(salesTax);
        return getVehicle().getPrice().add(tax).add(recordingFee).add(processingFee);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!finance) return new BigDecimal("0.0");
        BigDecimal price = getTotalPrice();
        if (price.compareTo(new BigDecimal("10000")) >= 0) {
            return (price.multiply(new BigDecimal("1").add(financeRate))).divide(new BigDecimal("48"), 2, RoundingMode.HALF_UP);
        } else {
            return (price.multiply(new BigDecimal("1").add(financeRate))).divide(new BigDecimal("24"), 2, RoundingMode.HALF_UP);
        }
    }

}
