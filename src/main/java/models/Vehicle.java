package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private double price;
    private double odometer;

    public Vehicle(String vin, int year, String make, String model, String type, String color, double odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    @Override
    public String toString() {
        return vin + " | " + year + " " + make + " " + model + " | Type: " + type + " | Color: " + color + " | Odometer: " + odometer + " | Price: $" + price;
    }
}
