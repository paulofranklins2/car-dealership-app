package persistence;

import models.Dealership;
import models.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class DealershipFileManager {
    private final Logger logger = LoggerFactory.getLogger(DealershipFileManager.class);
    private final String fileName = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (scanner.hasNextLine()) {
                String[] header = scanner.nextLine().split("\\|");
                dealership = new Dealership(header[0], header[1], header[2]);
                logger.info("Loaded dealership: {}", dealership);
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|");

                Vehicle vehicle = new Vehicle(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4],
                        data[5], Double.parseDouble(data[6]), new BigDecimal(data[7]));

                assert dealership != null;
                dealership.addVehicle(vehicle);

                logger.info("Loaded vehicle: {}", vehicle);

            }
        } catch (Exception e) {
            logger.debug("Failed to load dealership data", e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            for (Vehicle v : dealership.getVehicles()) {
                writer.printf("%s|%d|%s|%s|%s|%s|%.1f|%s\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getType(), v.getColor(), v.getOdometer(), v.getPrice().toPlainString());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
