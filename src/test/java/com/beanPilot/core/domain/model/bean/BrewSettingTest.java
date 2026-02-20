package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrewSettingTest {

    @Test
    @DisplayName("BrewSetting can be created with valid grindSize, dose, and extractionTime")
    void createsValidBrewSetting() {
        var grindSize = GrindSize.ofSize(5);
        var dose = Dose.ofGrams(18);
        var extractionTime = ExtractionTime.ofSeconds(25);
        
        var brewSetting = BrewSetting.of(grindSize, dose, extractionTime);

        assertEquals(grindSize, brewSetting.grindSize());
        assertEquals(dose, brewSetting.dose());
        assertEquals(extractionTime, brewSetting.extractionTime());
    }

    @Test
    @DisplayName("BrewSetting equality works correctly")
    void testEquality() {
        var grindSize1 = GrindSize.ofSize(5);
        var dose1 = Dose.ofGrams(18);
        var extractionTime1 = ExtractionTime.ofSeconds(25);
        
        var grindSize2 = GrindSize.ofSize(5);
        var dose2 = Dose.ofGrams(18);
        var extractionTime2 = ExtractionTime.ofSeconds(25);
        
        var brewSetting1 = BrewSetting.of(grindSize1, dose1, extractionTime1);
        var brewSetting2 = BrewSetting.of(grindSize2, dose2, extractionTime2);
        var brewSetting3 = BrewSetting.of(GrindSize.ofSize(7), dose1, extractionTime1);

        assertEquals(brewSetting1, brewSetting2);
        assertEquals(brewSetting1.hashCode(), brewSetting2.hashCode());
        
        assertNotEquals(brewSetting1, brewSetting3);
        
        assertEquals(brewSetting1, brewSetting1);
        
        assertNotEquals(brewSetting1, null);
    }

    @Test
    @DisplayName("BrewSetting with different grindSize should not be equal")
    void testInequalityWithDifferentGrindSize() {
        var dose = Dose.ofGrams(18);
        var extractionTime = ExtractionTime.ofSeconds(25);
        
        var brewSetting1 = BrewSetting.of(GrindSize.ofSize(5), dose, extractionTime);
        var brewSetting2 = BrewSetting.of(GrindSize.ofSize(7), dose, extractionTime);

        assertNotEquals(brewSetting1, brewSetting2);
    }

    @Test
    @DisplayName("BrewSetting with different dose should not be equal")
    void testInequalityWithDifferentDose() {
        var grindSize = GrindSize.ofSize(5);
        var extractionTime = ExtractionTime.ofSeconds(25);
        
        var brewSetting1 = BrewSetting.of(grindSize, Dose.ofGrams(18), extractionTime);
        var brewSetting2 = BrewSetting.of(grindSize, Dose.ofGrams(22), extractionTime);

        assertNotEquals(brewSetting1, brewSetting2);
    }

    @Test
    @DisplayName("BrewSetting with different extractionTime should not be equal")
    void testInequalityWithDifferentExtractionTime() {
        var grindSize = GrindSize.ofSize(5);
        var dose = Dose.ofGrams(18);
        
        var brewSetting1 = BrewSetting.of(grindSize, dose, ExtractionTime.ofSeconds(25));
        var brewSetting2 = BrewSetting.of(grindSize, dose, ExtractionTime.ofSeconds(30));

        assertNotEquals(brewSetting1, brewSetting2);
    }

    @Test
    @DisplayName("BrewSetting toString contains all properties")
    void testToString() {
        var grindSize = GrindSize.ofSize(6);
        var dose = Dose.ofGrams(20);
        var extractionTime = ExtractionTime.ofSeconds(28);
        var brewSetting = BrewSetting.of(grindSize, dose, extractionTime);

        String result = brewSetting.toString();
        
        assertTrue(result.contains("BrewSettings"));
        assertTrue(result.contains(grindSize.toString()));
        assertTrue(result.contains(dose.toString()));
        assertTrue(result.contains(extractionTime.toString()));
    }

    @Test
    @DisplayName("BrewSetting requires non-null parameters")
    void testNullParameterHandling() {
        var grindSize = GrindSize.ofSize(5);
        var dose = Dose.ofGrams(18);
        var extractionTime = ExtractionTime.ofSeconds(25);

        assertThrows(NullPointerException.class, () -> 
            BrewSetting.of(null, dose, extractionTime));
        
        assertThrows(NullPointerException.class, () -> 
            BrewSetting.of(grindSize, null, extractionTime));
        
        assertThrows(NullPointerException.class, () -> 
            BrewSetting.of(grindSize, dose, null));
    }

    @Test
    @DisplayName("BrewSetting can be created with various valid combinations")
    void testValidCombinations() {
        // Test with minimum values
        var minBrewSetting = BrewSetting.of(
            GrindSize.ofSize(2), 
            Dose.ofGrams(1), 
            ExtractionTime.ofSeconds(1)
        );
        assertNotNull(minBrewSetting);

        // Test with max realistic values
        var maxBrewSetting = BrewSetting.of(
            GrindSize.ofSize(10), 
            Dose.ofGrams(100), 
            ExtractionTime.ofSeconds(300)
        );
        assertNotNull(maxBrewSetting);

        // Test with "typical" espresso values
        var espressoSetting = BrewSetting.of(
            GrindSize.ofSize(2), 
            Dose.ofGrams(18), 
            ExtractionTime.ofSeconds(27)
        );
        assertNotNull(espressoSetting);

        // Test with typical pour-over values
        var pourOverSetting = BrewSetting.of(
            GrindSize.ofSize(6), 
            Dose.ofGrams(30), 
            ExtractionTime.ofSeconds(240)
        );
        assertNotNull(pourOverSetting);
    }
}
