package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceTest {
    
    @Test
    @DisplayName("ofPrice accepts valid values")
    void createsValidPrice() {
        var p1 = Price.ofValue(21.30, new java.util.Date());
        var p2 = Price.ofValue(19.30, new java.util.Date());
        var p3 = Price.ofValue(21.30, new java.util.Date());


        assertEquals(21.30, p1.getPrice());
        assertEquals(Price.ofValue(21.30, p1.getPurchaseDate()), p1);
        assertEquals(p1, p1);
        assertNotEquals(p2, p1);
        assertEquals(p3, p1);
        assertNotEquals(Price.ofValue(10.30, p1.getPurchaseDate()), p1);
        assertTrue(Price.ofValue(22.30, p1.getPurchaseDate()).isGreaterThan(p1));
        assertTrue(p1.isGreaterThan(p2));
    }

    @Test
    @DisplayName("ofPrice throws IllegalArgumentException for negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Price.ofValue(-20.10, new java.util.Date()));
        assertThrows(IllegalArgumentException.class, () -> Price.ofValue(-5, new java.util.Date()));
    }
}
