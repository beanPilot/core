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
        var p1 = Price.of(21.30, new java.util.Date());
        var p2 = Price.of(19.30, new java.util.Date());
        var p3 = Price.of(21.30, new java.util.Date());


        assertEquals(21.30, p1.getPrice());
        assertEquals(Price.of(21.30, p1.getPurchaseDate()), p1);
        assertEquals(p1, p1);
        assertNotEquals(p2, p1);
        assertEquals(p3, p1);
        assertNotEquals(Price.of(10.30, p1.getPurchaseDate()), p1);
        assertTrue(Price.of(22.30, p1.getPurchaseDate()).isGreaterThan(p1));
        assertTrue(p1.isGreaterThan(p2));
    }

    @Test
    @DisplayName("ofPrice throws IllegalArgumentException for negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Price.of(-20.10, new java.util.Date()));
        assertThrows(IllegalArgumentException.class, () -> Price.of(-5, new java.util.Date()));
    }
}
