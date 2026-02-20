package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoffeePackageTest {
    private final int testYear = 2024;
    private final int testMonth = 2;
    private final int testDay = 19;
    private final java.util.Date testDate = Date.from(LocalDate.of(testYear, testMonth, testDay).atStartOfDay(ZoneId.systemDefault()).toInstant());

    @Test
    @DisplayName("Package can be created with valid price, grams, and purchase date")
    void createsValidPackage() {
        var purchaseDate = testDate;
        var price = Price.ofValue(24.50, purchaseDate);
        var grams = 250;
        
        CoffeePackage packageObj = CoffeePackage.create(price, grams, purchaseDate);

        assertNotNull(packageObj.getId());
        assertFalse(packageObj.getId().toString().isBlank(), "id should not be blank");
        assertEquals(price, packageObj.getPrice());
        assertEquals(grams, packageObj.getInitialGrams());
        assertEquals(grams, packageObj.getRemainingGrams());
        assertEquals(purchaseDate, packageObj.getPurchaseDate());
        assertFalse(packageObj.isEmpty());
    }

    @Test
    @DisplayName("Package generates unique IDs")
    void generatesUniqueIds() {
        var purchaseDate = testDate;
        var price = Price.ofValue(20.00, purchaseDate);
        var grams = 500;
        
        CoffeePackage package1 = CoffeePackage.create(price, grams, purchaseDate);
        CoffeePackage package2 = CoffeePackage.create(price, grams, purchaseDate);

        assertNotEquals(package1.getId(), package2.getId());
    }

    @Test
    @DisplayName("Package consume reduces remaining grams")
    void consumeReducesRemainingGrams() {
        var purchaseDate = testDate;
        var price = Price.ofValue(30.00, purchaseDate);
        var initialGrams = 1000;
        var consumeAmount = 200;
        CoffeePackage packageObj = CoffeePackage.create(price, initialGrams, purchaseDate);

        packageObj.consume(consumeAmount);

        assertEquals(initialGrams, packageObj.getInitialGrams());
        assertEquals(initialGrams - consumeAmount, packageObj.getRemainingGrams());
        assertFalse(packageObj.isEmpty());
    }

    @Test
    @DisplayName("Package isEmpty returns true when all grams consumed")
    void isEmptyWhenAllGramsConsumed() {
        var purchaseDate = testDate;
        var price = Price.ofValue(15.00, purchaseDate);
        var grams = 100;
        CoffeePackage packageObj = CoffeePackage.create(price, grams, purchaseDate);

        packageObj.consume(grams);

        assertEquals(0, packageObj.getRemainingGrams());
        assertTrue(packageObj.isEmpty());
    }

    @Test
    @DisplayName("Package consume multiple times works correctly")
    void consumeMultipleTimes() {
        var purchaseDate = testDate;
        var price = Price.ofValue(25.00, purchaseDate);
        var initialGrams = 500;
        CoffeePackage packageObj = CoffeePackage.create(price, initialGrams, purchaseDate);

        packageObj.consume(100);
        assertEquals(400, packageObj.getRemainingGrams());
        
        packageObj.consume(150);
        assertEquals(250, packageObj.getRemainingGrams());
        
        packageObj.consume(250);
        assertEquals(0, packageObj.getRemainingGrams());
        assertTrue(packageObj.isEmpty());
    }

    @Test
    @DisplayName("Package creation throws IllegalArgumentException for invalid grams")
    void rejectsInvalidGrams() {
        var purchaseDate = testDate;
        var price = Price.ofValue(20.00, purchaseDate);

        assertThrows(IllegalArgumentException.class, () -> 
            CoffeePackage.create(price, 0, purchaseDate));
        
        assertThrows(IllegalArgumentException.class, () -> 
            CoffeePackage.create(price, -100, purchaseDate));
    }

    @Test
    @DisplayName("Package consume throws IllegalArgumentException for invalid amounts")
    void consumeRejectsInvalidAmounts() {
        var purchaseDate = testDate;
        var price = Price.ofValue(20.00, purchaseDate);
        CoffeePackage packageObj = CoffeePackage.create(price, 250, purchaseDate);

        assertThrows(IllegalArgumentException.class, () -> 
            packageObj.consume(0));
        
        assertThrows(IllegalArgumentException.class, () -> 
            packageObj.consume(-50));
    }

    @Test
    @DisplayName("Package consume throws IllegalArgumentException when consuming more than remaining")
    void consumeRejectsTooMuchConsumption() {
        var purchaseDate = testDate;
        var price = Price.ofValue(20.00, purchaseDate);
        CoffeePackage packageObj = CoffeePackage.create(price, 100, purchaseDate);

        assertThrows(IllegalArgumentException.class, () -> 
            packageObj.consume(101));
        
        packageObj.consume(50);
        assertThrows(IllegalArgumentException.class, () -> 
            packageObj.consume(51));
    }

    @Test
    @DisplayName("Package creation requires non-null parameters")
    void testNullParameterHandling() {
        var purchaseDate = testDate;
        var grams = 250;

        assertThrows(NullPointerException.class, () -> 
            CoffeePackage.create(null, grams, purchaseDate));
    }

    @Test
    @DisplayName("Package can handle typical coffee bag sizes")
    void testTypicalCoffeeBagSizes() {
        var purchaseDate = testDate;
        var price = Price.ofValue(12.50, purchaseDate);

        CoffeePackage smallBag = CoffeePackage.create(price, 250, purchaseDate);
        assertEquals(250, smallBag.getInitialGrams());
        
        CoffeePackage standardBag = CoffeePackage.create(price, 500, purchaseDate);
        assertEquals(500, standardBag.getInitialGrams());
        
        CoffeePackage largeBag = CoffeePackage.create(price, 1000, purchaseDate);
        assertEquals(1000, largeBag.getInitialGrams());
        
        assertFalse(smallBag.isEmpty());
        assertFalse(standardBag.isEmpty());
        assertFalse(largeBag.isEmpty());
    }
}
