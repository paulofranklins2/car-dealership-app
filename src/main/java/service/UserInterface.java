package service;

import model.Dealership;
import model.Vehicle;

import static app.AppContext.scanner;
import static app.ConsoleUI.mainMenuLogic;
import static app.InputManager.*;

public class UserInterface {
    Dealership dealership;

    public void display() {
        init();
        mainMenuLogic(this);
    }

    private void init() {
        this.dealership = new DealershipFileManager().getDealership();
    }

    public void processGetByPriceRequest() {
        var lowerBound = readBigDecimalFromUser("Enter the lower bound of the price range: ");
        var upperBound = readBigDecimalFromUser("Enter the upper bound of the price range: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getPrice().compareTo(lowerBound) >= 0 && vehicle.getPrice().compareTo(upperBound) <= 0)
                System.out.println(vehicle);
        scanner.nextLine();
        enterToContinue();
    }

    public void processGetByMakeModelRequest() {
        scanner.nextLine();
        var make = readStringFromUser("Enter a make: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getMake().equalsIgnoreCase(make)) System.out.println(vehicle);
        enterToContinue();
    }

    public void processGetByYearRequest() {
        scanner.nextLine();
        var year = readIntFromUser("Enter a year: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getYear() == year) System.out.println(vehicle);
        scanner.nextLine();
        enterToContinue();
    }

    public void processGetByColorRequest() {
        scanner.nextLine();
        var color = readStringFromUser("Enter a color: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getColor().equalsIgnoreCase(color)) System.out.println(vehicle);
        enterToContinue();
    }

    public void processGetByMileageRequest() {
        scanner.nextLine();
        var lowerBound = readIntFromUser("Enter the lower bound of the mileage range: ");
        var upperBound = readIntFromUser("Enter the upper bound of the mileage range: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getOdometer() >= lowerBound && vehicle.getOdometer() <= upperBound) System.out.println(vehicle);
        scanner.nextLine();
        enterToContinue();
    }

    public void processGetByVehicleTypeRequest() {
        scanner.nextLine();
        var type = readStringFromUser("Enter a type: ");
        for (Vehicle vehicle : dealership.getAllVehicles())
            if (vehicle.getType().equalsIgnoreCase(type)) System.out.println(vehicle);
        enterToContinue();
    }

    public void processGetAllVehiclesRequest() {
        System.out.println(dealership);

        for (Vehicle vehicle : dealership.getAllVehicles())
            System.out.println(vehicle);
        scanner.nextLine();
        enterToContinue();
    }

    public void processAddVehicleRequest() {
    }

    public void processRemoveVehicleRequest() {
    }
}
