package model;

import org.junit.jupiter.api.Test;
import service.DealershipFileManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DealershipTest {

    Dealership dealership = new Dealership("xx", "yy", "zz");
    DealershipFileManager dealershipFileManager = new DealershipFileManager();

    @Test
    void addVehicle() {
        dealership.addVehicle(new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealership.addVehicle(new Vehicle("z", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
    }

    @Test
    void removeVehicle() {
        dealership.removeVehicle("a");
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getAllVehicles().size(), 0);
    }

    @Test
    void getAllVehicles() {
        dealership.getAllVehicles();
        assertEquals(dealership.getAllVehicles().size(), 0);
        dealership.addVehicle(new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealership.addVehicle(new Vehicle("z", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getAllVehicles().size(), 2);
        dealership.removeVehicle("a");
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getAllVehicles().size(), 1);
        dealership.removeVehicle("z");
    }

    @Test
    void getVehicleByPrice() {
        dealership.addVehicle(new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealership.addVehicle(new Vehicle("z", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getVehicleByPrice(new BigDecimal("10"), new BigDecimal("20")).size(), 2);
        dealership.removeVehicle("a");
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getVehicleByPrice(new BigDecimal("10"), new BigDecimal("20")).size(), 1);
    }

    @Test
    void testToString() {
        assertEquals(dealership.toString(), dealership.getName() + " | " + dealership.getAddress() + " | " + dealership.getPhone());
    }

    @Test
    void getName() {
        assertEquals(dealership.getName(), "xx");
    }

    @Test
    void getAddress() {
        assertEquals(dealership.getAddress(), "yy");
    }

    @Test
    void getPhone() {
        assertEquals(dealership.getPhone(), "zz");
    }

    @Test
    void getInventory() {
        assertEquals(dealership.getInventory().size(), 0);
        dealership.addVehicle(new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealership.addVehicle(new Vehicle("z", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getInventory().size(), 2);
        dealership.removeVehicle("a");
        dealershipFileManager.saveDealershipOverWrite(dealership);
        assertEquals(dealership.getInventory().size(), 1);
        dealership.removeVehicle("z");
    }

    @Test
    void getDealershipFileManager() {
//        assertEquals(dealership.getDealershipFileManager(), dealershipFileManager);
//        dealershipFileManager.saveDealershipOverWrite(dealership);
//        assertEquals(dealership.getDealershipFileManager(), dealershipFileManager);
//        dealership.removeVehicle("a");
//        dealershipFileManager.saveDealershipOverWrite(dealership);
//        assertEquals(dealership.getDealershipFileManager(), dealershipFileManager);
    }

    @Test
    void setName() {
        dealership.setName("xy");
        assertEquals("xy", dealership.getName());
    }

    @Test
    void setAddress() {
        dealership.setAddress("yx");
        assertEquals("yx", dealership.getAddress());
    }

    @Test
    void setPhone() {
        dealership.setPhone("zy");
        assertEquals("zy", dealership.getPhone());
    }

    @Test
    void setInventory() {
//        dealership.setInventory(null);
//        assertEquals(dealership.getInventory().size(), 0);
//        dealership.addVehicle(new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
//        dealership.addVehicle(new Vehicle("z", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20")));
    }

    @Test
    void setDealershipFileManager() {
//        Dealership x = new Dealership("a", "b", "c");
//        dealershipFileManager.saveDealershipOverWrite(x);
//        assertEquals(dealership.getDealershipFileManager(), dealershipFileManager);
//        dealership.removeVehicle("a");
    }
}