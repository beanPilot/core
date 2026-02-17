package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GrindSizeTest {

    @Test
    @DisplayName("ofSize accepts valid values within range of ")
    void createsValidGrindSize() {
        var m = GrindSize.ofSize(5);

        assertEquals(5, m.getSize());
        assertEquals(GrindSize.ofSize(5), m);
        assertNotEquals(GrindSize.ofSize(6), m);
        assertTrue(GrindSize.ofSize(6).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofSize throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> GrindSize.ofSize(0));
        assertThrows(IllegalArgumentException.class, () -> GrindSize.ofSize(-5));
    }

    @Test
    @DisplayName("ofSize throws IllegalArgumentException for unrealistically large values")
    void rejectsTooLargeValues() {
        assertThrows(IllegalArgumentException.class, () -> GrindSize.ofSize(1000));
    }
}