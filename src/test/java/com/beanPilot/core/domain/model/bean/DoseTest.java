package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DoseTest {

    @Test
    @DisplayName("ofGramm accepts positive, \"realistic\" values (up to 100g)")
    void createsValidDose() {
        var m = Dose.ofgram(18);

        assertEquals(18, m.ingram());
        assertEquals(Dose.ofgram(18), m);
        assertNotEquals(Dose.ofgram(19), m);
        assertTrue(Dose.ofgram(20).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofGramm throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Dose.ofgram(0));
        assertThrows(IllegalArgumentException.class, () -> Dose.ofgram(-5));
    }

    @Test
    @DisplayName("ofGramm throws IllegalArgumentException for unrealistically large values")
    void rejectsTooLargeValues() {
        assertThrows(IllegalArgumentException.class, () -> Dose.ofgram(1000));
    }
}