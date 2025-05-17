package contracts;


import lombok.Getter;
import models.Vehicle;

@Getter
public class SalesContract extends Contract {
    private final boolean finance;
    private final double salesTax = 0.05;
    private final double recordingFee = 100;
    private final double processingFee = getVehicle().getPrice() < 10000 ? 295 : 495;
    private final double financeRate = getTotalPrice() >= 10000 ? 0.0425 : 0.0525;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean finance) {
        super(date, name, email, vehicle);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double tax = getVehicle().getPrice() * salesTax;
        return getVehicle().getPrice() + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;
        double price = getTotalPrice();
        if (price >= 10000) {
            return (price * (1 + financeRate)) / 48;
        } else {
            return (price * (1 + financeRate)) / 24;
        }
    }

}
