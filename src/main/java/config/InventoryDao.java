package config;

import models.Vehicle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryDao implements InventoryRepository {
    private final Connection connection;

    public InventoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(new Vehicle(
                        resultSet.getString("vin"),
                        resultSet.getInt("year"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("odometer"),
                        resultSet.getBigDecimal("price")));
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getByModel(String model) {
        return getAll().stream().filter(vehicle -> vehicle.getModel().equals(model)).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getByYear(int year) {
        return getAll().stream().filter(vehicle -> vehicle.getYear() == year).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getByColor(String color) {
        return getAll().stream().filter(vehicle -> vehicle.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getByMileageRange(int minimumMileage, int maximumMileage) {
        return getAll().stream()
                .filter(vehicle -> vehicle.getPrice().compareTo(BigDecimal.valueOf(minimumMileage)) >= 0
                        && vehicle.getPrice().compareTo(BigDecimal.valueOf(maximumMileage)) <= 0)
                .toList();
    }

    @Override
    public List<Vehicle> getByType(String type) {
        return getAll().stream().filter(vehicle -> vehicle.getType().equals(type)).collect(Collectors.toList());
    }

    @Override
    public void add(Vehicle vehicle) {
//      public Vehicle(String vin, int year, String make, String model, String type, String color, double odometer, BigDecimal price) {
        String sql = "insert into vehicle (vin, year, make, model, type, color, odometer, price)  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setDouble(7, vehicle.getOdometer());
            preparedStatement.setBigDecimal(8, vehicle.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

    }

    @Override
    public void update(String vin, Vehicle vehicle) {
//        String sql = "UPDATE vehicle set vin = ? where vin = ?";
//
//        try (PreparedStatement preparedStatement = connection.createStatement()) {
//            preparedStatement.setString(1, vin);
//            preparedStatement.setString(2, vehicle.getVin());
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage());
//        }

    }

    @Override
    public void delete(String vin) {
        String sql = "delete from vehicle where vin = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
    }
}
