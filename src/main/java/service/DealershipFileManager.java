package service;

import model.Dealership;
import model.Vehicle;

import java.io.*;
import java.math.BigDecimal;

public class DealershipFileManager {
    private final String PATH_INVENTORY = "src/main/resources/inventory.csv";

    public Dealership getDealership() {

        Dealership dealership = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_INVENTORY))) {

            String line;
            boolean firstLine = true;

            while ((line = bufferedReader.readLine()) != null) {

                var split = line.split("\\|");

                if (firstLine && split.length == 3) {
                    String name = split[0].replace("_", " ");
                    String address = split[1];
                    String phone = split[2];

                    dealership = new Dealership(name, address, phone);
                    firstLine = false;
                } else if (split.length == 8 && dealership != null) {
                    String vin = split[0];
                    int year = Integer.parseInt(split[1]);
                    String make = split[2];
                    String model = split[3];
                    String type = split[4];
                    String color = split[5];
                    double odometer = Double.parseDouble(split[6]);
                    BigDecimal price = new BigDecimal(split[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                    dealership.addVehicle(vehicle);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("404 dealer not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealershipOverWrite(Dealership dealership) {

        try (FileWriter fileWriter = new FileWriter(PATH_INVENTORY, false)) { // false = overwrite
            // Save dealer info
            //My Dealership|123 Main St|123-456-7890
            fileWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");

            // Save each vehicle
            //10112|1993|Ford|Explorer|SUV|Red|525123|995.00
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                fileWriter.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Failed to save dealership: " + e.getMessage());
        }
    }
}
