package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntensityTest {

    @Test
    @DisplayName("ofValue accepts valid values within range of ")
    void createsValidIntensity() {
        var m = Intensity.ofValue(5);

        assertEquals(5, m.getValue());
        assertEquals(Intensity.ofValue(5), m);
        assertNotEquals(Intensity.ofValue(6), m);
        assertTrue(Intensity.ofValue(6).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofValue throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Intensity.ofValue(0));
        assertThrows(IllegalArgumentException.class, () -> Intensity.ofValue(-5));
    }

    @Test
    @DisplayName("ofValue throws IllegalArgumentException for values greater than MAX_SIZE")
    void rejectsTooLargeValues() {
        assertThrows(IllegalArgumentException.class, () -> Intensity.ofValue(Intensity.getMaxValue() + 1));
    }
}