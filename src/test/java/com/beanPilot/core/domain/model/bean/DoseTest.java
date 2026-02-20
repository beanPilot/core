package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoseTest {

    @Test
    @DisplayName("ofGramsm accepts positive, \"realistic\" values (up to 100g)")
    void createsValidDose() {
        var m = Dose.ofGrams(18);

        assertEquals(18, m.inGram());
        assertEquals(Dose.ofGrams(18), m);
        assertNotEquals(Dose.ofGrams(19), m);
        assertTrue(Dose.ofGrams(20).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofGramsm throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Dose.ofGrams(0));
        assertThrows(IllegalArgumentException.class, () -> Dose.ofGrams(-5));
    }

    @Test
    @DisplayName("ofGramsm throws IllegalArgumentException for unrealistically large values")
    void rejectsTooLargeValues() {
        assertThrows(IllegalArgumentException.class, () -> Dose.ofGrams(1000));
    }
}