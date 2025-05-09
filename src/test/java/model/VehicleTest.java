package model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Vehicle x = new Vehicle("a", 10, "b", "c" ,"d", "e", 10, new BigDecimal("20"));

    @Test
    void testToString() {
        assertEquals(x.toString(), x.getVin() + " | " + x.getYear() + " | " + x.getMake() + " | " + x.getModel() + " | " + x.getType() + " | " + x.getColor() + " | " + x.getOdometer() + " | " + x.getPrice());
    }

    @Test
    void getVin() {
        assertEquals("a", x.getVin());
    }

    @Test
    void getYear() {
        assertEquals(10, x.getYear());
    }

    @Test
    void getMake() {
        assertEquals("b", x.getMake());
    }

    @Test
    void getModel() {
        assertEquals("c", x.getModel());
    }

    @Test
    void getType() {
        assertEquals("d", x.getType());
    }

    @Test
    void getColor() {
        assertEquals("e", x.getColor());
    }

    @Test
    void getOdometer() {
        assertEquals(10, x.getOdometer());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal("20"), x.getPrice());
    }

    @Test
    void setVin() {
        x.setVin("b");
        assertEquals("b", x.getVin());
    }

    @Test
    void setYear() {
        x.setYear(100);
        assertEquals(100, x.getYear());

    }

    @Test
    void setMake() {
        x.setMake("a");
        assertEquals("a", x.getMake());
    }

    @Test
    void setModel() {
        x.setModel("z");
        assertEquals("z", x.getModel());
    }

    @Test
    void setType() {
        x.setType("v");
        assertEquals("v", x.getType());
    }

    @Test
    void setColor() {
        x.setColor("f");
        assertEquals("f", x.getColor());
    }

    @Test
    void setOdometer() {
        x.setOdometer(1110);
        assertEquals(1110, x.getOdometer());
    }

    @Test
    void setPrice() {
        x.setPrice(new BigDecimal("1234123412"));
        assertEquals(new BigDecimal("1234123412"), x.getPrice());
    }
}