package app;

import service.UserInterface;

import static app.InputManager.readIntFromUser;

public class ConsoleUI {
    public static void mainMenuUI() {
        System.out.println("\n==== Dealership Menu ====");
        System.out.println("[1] Find vehicles within a price range");
        System.out.println("[2] Find vehicles by make / model");
        System.out.println("[3] Find vehicles by year range");
        System.out.println("[4] Find vehicles by color");
        System.out.println("[5] Find vehicles by mileage range");
        System.out.println("[6] Find vehicles by type (sedan, truck, SUV, van)");
        System.out.println("[7] List ALL vehicles");
        System.out.println("[8] Add a vehicle");
        System.out.println("[9] Remove a vehicle");
        System.out.println("[99] Quit");
    }

    public static void mainMenuLogic(UserInterface userInterface) {
        while (true) {
            mainMenuUI();
            var option = readIntFromUser("Select an option: ");
            switch (option) {
                case 1 -> userInterface.processGetByPriceRequest();
                case 2 -> userInterface.processGetByMakeModelRequest();
                case 3 -> userInterface.processGetByYearRequest();
                case 4 -> userInterface.processGetByColorRequest();
                case 5 -> userInterface.processGetByMileageRequest();
                case 6 -> userInterface.processGetByVehicleTypeRequest();
                case 7 -> userInterface.processGetAllVehiclesRequest();
                case 8 -> userInterface.processAddVehicleRequest();
                case 9 -> userInterface.processRemoveVehicleRequest();
                case 99 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

}
