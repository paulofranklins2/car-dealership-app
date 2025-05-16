package persistence;

import models.Dealership;
import models.Vehicle;

import java.io.*;
import java.util.*;

public class DealershipFileManager {
    private final String fileName = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (scanner.hasNextLine()) {
                String[] header = scanner.nextLine().split("\\|");
                dealership = new Dealership(header[0], header[1], header[2]);
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|");

                dealership.addVehicle( new Vehicle(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4],
                        data[5], Double.parseDouble(data[6]), Double.parseDouble(data[7])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            for (Vehicle v : dealership.getVehicles()) {
                writer.printf("%s|%d|%s|%s|%s|%s|%.1f|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(), v.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
