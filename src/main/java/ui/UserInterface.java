package ui;

import contracts.Contract;
import contracts.LeaseContract;
import contracts.SalesContract;
import models.Dealership;
import models.Vehicle;
import persistence.ContractFileManager;
import persistence.DealershipFileManager;
import service.DealershipService;

import java.text.SimpleDateFormat;
import java.util.*;

import static ui.InputManager.*;
import static ui.InputManager.readStringFromUser;

public class UserInterface {
    private Dealership dealership;
    private DealershipService dealershipService;

    public void display() {
        init();
        while (true) {
            showMenu();
            int choice = readIntFromUser("Choice: ");
            scanner.nextLine(); //consuming int input left over
            switch (choice) {
                case 1 -> displayAllVehicles();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 8 -> addVehicle();
                case 9 -> removeVehicle();
                case 10 -> sellOrLeaseVehicle();
                case 99 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    private void showMenu() {
        System.out.println("\n=== Dealership Menu ===");
        System.out.println("[1] - List all vehicles");
        System.out.println("[2] Find vehicles by make / model");
        System.out.println("[3] Find vehicles by year range");
        System.out.println("[4] Find vehicles by color");
        System.out.println("[5] Find vehicles by mileage range");
        System.out.println("[6] Find vehicles by type (sedan, truck, SUV, van)");
        System.out.println("[7] List ALL vehicles");
        System.out.println("[8] - Add vehicle");
        System.out.println("[9] - Remove vehicle");
        System.out.println("[10] - Sell/Lease vehicle");
        System.out.println("[99] - Quit");
    }

    private void displayAllVehicles() {
        dealership.getVehicles().forEach(System.out::println);
    }

    public void processGetByMakeModelRequest() {
        var option = readStringFromUser("Enter make/model: ");
        dealershipService = new DealershipService(dealership);
        dealershipService.getVehiclesByMakeOrModel(option).forEach(System.out::println);
        enterToContinue();
    }

    public void processGetByYearRequest() {
        var lowerBoundYear = readIntFromUser("Enter lower bound year: ");
        var upperBoundYear = readIntFromUser("Enter upper bound year: ");
        scanner.nextLine();
        dealershipService = new DealershipService(dealership);
        dealershipService.getVehiclesByYearRange(lowerBoundYear, upperBoundYear).forEach(System.out::println);
        enterToContinue();
    }

    public void processGetByColorRequest() {
        var color = readStringFromUser("Enter color: ");
        dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getColor().equals(color))
                .forEach(System.out::println);
        enterToContinue();
    }

    public void processGetByMileageRequest() {
        var lowerBound = readIntFromUser("Enter the lower bound of the mileage range: ");
        var upperBound = readIntFromUser("Enter the upper bound of the mileage range: ");
        scanner.nextLine();
        dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getOdometer() >= lowerBound && vehicle.getOdometer() <= upperBound)
                .forEach(System.out::println);
        enterToContinue();
    }

    public void processGetByVehicleTypeRequest() {
        var type = readStringFromUser("Enter type: ");
        dealership.getVehicles().stream()
                .filter(vehicle -> vehicle.getType().equals(type))
                .forEach(System.out::println);
        enterToContinue();
    }

    private void addVehicle() {
        System.out.println("Enter details (VIN, year, make, model, type, color, odometer, price):");
        String vin = readStringFromUser("Vin: ");
        int year = readIntFromUser("Year: ");
        scanner.nextLine();
        String make = readStringFromUser("Make: ");
        String model = readStringFromUser("Model: ");
        String type = readStringFromUser("Type: ");
        String color = readStringFromUser("Color: ");
        double odometer = readDoubleFromUser("Odometer: ");
        double price = readDoubleFromUser("Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        boolean added = dealershipService.addVehicle(vehicle);

        if (added)
            System.out.println("Vehicle added successfully.");
        else
            System.out.println("Vehicle with this VIN already exists.");
    }

    private void removeVehicle() {
        var vin = readStringFromUser("Vin: ");
        dealershipService = new DealershipService(dealership);
        boolean removed = dealershipService.removeVehicle(vin);

        if (removed)
            System.out.println("Vehicle removed.");
        else
            System.out.println("Vehicle not found.");
        enterToContinue();
    }

    private void sellOrLeaseVehicle() {
        var vin = readStringFromUser("Vin: ");

        Vehicle vehicle = dealership.getVehicles().stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst().orElse(null);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        String name = readStringFromUser("Customer name: ");
        String email = readStringFromUser("Customer email: ");
        System.out.println("[S] - Sale");
        System.out.println("[L] - Lease");
        String type = readStringFromUser("Choice: ").toUpperCase();

        Contract contract;
        if (type.equals("S")) {
            System.out.print("Finance (Y/N)? ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("Y");
            contract = new SalesContract(getToday(), name, email, vehicle, finance);
        } else {
            if (vehicle.getYear() < Calendar.getInstance().get(Calendar.YEAR) - 3) {
                System.out.println("Cannot lease vehicles older than 3 years.");
                return;
            }
            contract = new LeaseContract(getToday(), name, email, vehicle);
        }

        new ContractFileManager().saveContract(contract);
        dealership.removeVehicle(vehicle);
        new DealershipFileManager().saveDealership(dealership);
        System.out.println("Transaction recorded.");
    }

    private String getToday() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
