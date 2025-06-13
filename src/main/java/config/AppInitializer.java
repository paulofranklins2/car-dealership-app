package config;

import service.InventoryService;
import ui.DBUserInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class AppInitializer {

    public void run(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.checkArgs(args);

        try (Connection connection = dataManager.getDataSource(args).getConnection()) {
            InventoryRepository inventoryRepository = new InventoryDao(connection);
            InventoryService inventoryService = new InventoryService(inventoryRepository);
            DBUserInterface dbUserInterface= new DBUserInterface(inventoryService);

            dbUserInterface.init();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }
}
