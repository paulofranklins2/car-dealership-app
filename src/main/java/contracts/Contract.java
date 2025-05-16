package contracts;


import lombok.Getter;
import models.Vehicle;

@Getter
public abstract class Contract {
    private final String date;
    private final String customerName;
    private final String customerEmail;
    private final Vehicle vehicle;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
