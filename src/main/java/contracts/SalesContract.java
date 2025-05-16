package contracts;


import lombok.Getter;
import models.Vehicle;

@Getter
public class SalesContract extends Contract {
    private final boolean finance;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean finance) {
        super(date, name, email, vehicle);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double tax = getVehicle().getPrice() * 0.05;
        double recordingFee = 100;
        double processingFee = getVehicle().getPrice() < 10000 ? 295 : 495;
        return getVehicle().getPrice() + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;
        double price = getTotalPrice();
        if (price >= 10000) {
            return (price * (1 + 0.0425)) / 48;
        } else {
            return (price * (1 + 0.0525)) / 24;
        }
    }

}
