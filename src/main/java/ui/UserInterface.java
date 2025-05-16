package ui;

import contracts.Contract;
import contracts.LeaseContract;
import contracts.SalesContract;
import models.Dealership;
import models.Vehicle;
import persistence.ContractFileManager;
import persistence.DealershipFileManager;

import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume
            switch (choice) {
                case 1 -> displayAllVehicles();
                case 8 -> addVehicle();
                case 9 -> removeVehicle();
                case 10 -> sellOrLeaseVehicle();
                case 99 -> { return; }
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
        System.out.println("1 - List all vehicles");
        System.out.println("8 - Add vehicle");
        System.out.println("9 - Remove vehicle");
        System.out.println("10 - Sell/Lease vehicle");
        System.out.println("99 - Quit");
    }

    private void displayAllVehicles() {
        dealership.getVehicles().forEach(System.out::println);
    }

    private void addVehicle() {
        System.out.println("Enter details (VIN, year, make, model, type, color, odometer, price):");
        Vehicle v = new Vehicle(
                scanner.nextLine(),
                scanner.nextInt(), scanner.nextLine(),
                scanner.nextLine(), scanner.nextLine(),
                scanner.nextLine(), scanner.nextInt(),
                scanner.nextDouble());
        dealership.addVehicle(v);
        new DealershipFileManager().saveDealership(dealership);
    }

    private void removeVehicle() {
        System.out.print("Enter VIN to remove: ");
        String vin = scanner.nextLine();
        Vehicle target = dealership.getVehicles().stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst().orElse(null);
        if (target != null) {
            dealership.removeVehicle(target);
            new DealershipFileManager().saveDealership(dealership);
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void sellOrLeaseVehicle() {
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        Vehicle vehicle = dealership.getVehicles().stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst().orElse(null);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Customer email: ");
        String email = scanner.nextLine();
        System.out.print("Is this a Sale or Lease (S/L)? ");
        String type = scanner.nextLine().toUpperCase();

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
        return new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
