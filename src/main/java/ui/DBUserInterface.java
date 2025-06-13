package ui;

import service.InventoryService;

import static ui.InputManager.readStringFromUser;

public class DBUserInterface {
    private final InventoryService inventoryService;

    public DBUserInterface(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void init() {
        while (true) {
            menu();
            int choice = Integer.parseInt(readStringFromUser("Choice: "));
            switch (choice) {
                case 1 -> inventoryService.getAllVehicles() ;
                case 2 -> inventoryService.getAllByModels();
                case 3 -> inventoryService.getAllByYear();
                case 4 -> inventoryService.getAllByColor();
                case 5 -> inventoryService.getAllByMilageRange();
                case 6 -> inventoryService.getAllByType();
                case 7 -> inventoryService.addVehicle();
                case 8 -> inventoryService.deleteVehicle();
//                case 10 -> sellOrLeaseVehicle();
                case 99 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void menu() {
        System.out.println("\n=== Dealership Menu ===");
        System.out.println("[1] - List all vehicles");
        System.out.println("[2] - List all vehicles by Model");
        System.out.println("[3] - List all vehicles by Year");
        System.out.println("[4] - List all vehicles by Color");
        System.out.println("[5] - List all vehicles by Milage Range");
        System.out.println("[6] - List all vehicles by Type");
        System.out.println("[7] - Add vehicle");
        System.out.println("[8] - Remove vehicle");
//        System.out.println("[10] - Sell/Lease vehicle");
        System.out.println("[99] - Quit");
    }
}
