package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BitternessTest {

    @Test
    @DisplayName("ofValue accepts valid values within range of ")
    void createsValidBitterness() {
        var m = Bitterness.ofValue(5);

        assertEquals(5, m.getValue());
        assertEquals(Bitterness.ofValue(5), m);
        assertNotEquals(Bitterness.ofValue(6), m);
        assertTrue(Bitterness.ofValue(6).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofValue throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> Bitterness.ofValue(0));
        assertThrows(IllegalArgumentException.class, () -> Bitterness.ofValue(-5));
    }

    @Test
    @DisplayName("ofValue throws IllegalArgumentException for values greater than MAX_SIZE")
    void rejectsTooLargeValues() {
        assertThrows(IllegalArgumentException.class, () -> Bitterness.ofValue(Bitterness.getMaxValue() + 1));
    }
}