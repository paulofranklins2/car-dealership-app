package service;

import config.InventoryRepository;
import models.Vehicle;

import java.math.BigDecimal;

import static ui.InputManager.readStringFromUser;

public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void getAllVehicles() {
        inventoryRepository.getAll().forEach(System.out::println);
    }

    public void getAllByModels() {
        String model = readStringFromUser("Enter Model: ");
        inventoryRepository.getByModel(model).forEach(System.out::println);
    }

    public void getAllByYear() {
        String year = readStringFromUser("Enter Year: ");
        inventoryRepository.getByYear(Integer.parseInt(year)).forEach(System.out::println);
    }

    public void getAllByColor() {
        String color = readStringFromUser("Enter Color: ");
        inventoryRepository.getByColor(color).forEach(System.out::println);
    }

    public void getAllByMilageRange() {
        String lowerBoundMilage = readStringFromUser("Enter Lower Bound Milage Range: ");
        String upperBoundMilage = readStringFromUser("Enter Upper Bound Milage Range: ");
        inventoryRepository.getByMileageRange(Integer.getInteger(lowerBoundMilage), Integer.getInteger(upperBoundMilage)).forEach(System.out::println);
    }

    public void getAllByType() {
        String type = readStringFromUser("Enter Type: ");
        inventoryRepository.getByType(type).forEach(System.out::println);
    }

    public void addVehicle() {
        String vin = readStringFromUser("Enter Vin: ");
        int year = Integer.parseInt(readStringFromUser("Enter Year: "));
        String make = readStringFromUser("Enter Make: ");
        String model = readStringFromUser("Enter Model: ");
        String type = readStringFromUser("Enter Type: ");
        String color = readStringFromUser("Enter Color: ");
        double odometer = Double.parseDouble(readStringFromUser("Enter Mileage: "));
        BigDecimal price = new BigDecimal(readStringFromUser("Enter Price: "));
        System.out.println("vin: " + vin + "\nyear: " + year + "\nmake: " + make + "\nmodel: " + model + "\ntype: " + type + "\ncolor: " + color + "\nodometer: " + odometer + "\nprice: " + price );

//      public Vehicle(String vin, int year, String make, String model, String type, String color, double odometer, BigDecimal price) {
        inventoryRepository.add(new Vehicle(vin, year, make, model, type, color, odometer, price));
    }

    public void deleteVehicle() {
        String vin = readStringFromUser("Enter Vin: ");
        inventoryRepository.delete(vin);
    }
}
