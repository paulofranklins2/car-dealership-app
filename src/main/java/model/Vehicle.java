package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private double odometer;
    private BigDecimal price;

    @Override
    public String toString() {
        return vin + " | " + year + " | " + make + " | " + model + " | " + type + " | " + color + " | " + odometer + " | " + price;
    }
}
