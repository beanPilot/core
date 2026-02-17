package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtractionTimeTest {

    @Test
    @DisplayName("ofSeconds accepts valid values within range of ")
    void createsValidExtractionTime() {
        var m = ExtractionTime.ofSeconds(5);
        assertEquals(5, m.getSeconds());
        assertEquals(ExtractionTime.ofSeconds(5), m);
        assertNotEquals(ExtractionTime.ofSeconds(6), m);
        assertTrue(ExtractionTime.ofSeconds(6).isGreaterThan(m));
    }

    @Test
    @DisplayName("ofSeconds throws IllegalArgumentException for 0/negative values")
    void rejectsNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> ExtractionTime.ofSeconds(0));
        assertThrows(IllegalArgumentException.class, () -> ExtractionTime.ofSeconds(-5));
    }
}
