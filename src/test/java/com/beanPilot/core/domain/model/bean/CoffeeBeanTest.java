package com.beanpilot.core.domain.model.bean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeBeanTest {
    private final int testYear = 2024;
    private final int testMonth = 2;
    private final int testDay = 19;
    private final java.util.Date testDate = Date.from(LocalDate.of(testYear, testMonth, testDay).atStartOfDay(ZoneId.systemDefault()).toInstant());


    private BrewSetting defaultBrewSetting() {
        return BrewSetting.of(GrindSize.ofSize(4), Dose.ofGrams(18), ExtractionTime.ofSeconds(28));
    }

    private FlavorProfile defaultFlavorProfile() {
        return FlavorProfile.of(
                Flavor.BERRY,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );
    }

    private CoffeeBean newBean() {
        return CoffeeBean.create(
                "Ethiopia Guji",
                "Acme Roastery",
                defaultBrewSetting(),
                defaultFlavorProfile()
        );
    }

    @Test
    @DisplayName("create generates a new CoffeeBean with valid properties")
    void createBeanWorks() {
        CoffeeBean bean = newBean();

        assertNotNull(bean.getId(), "id must be generated");
        assertEquals("Ethiopia Guji", bean.getName());
        assertEquals("Acme Roastery", bean.getRoastery());
        assertNotNull(bean.getBrewSetting());
        assertNotNull(bean.getFlavorProfile());
        assertTrue(bean.getPackages().isEmpty(), "new beans should have no packages yet");
    }

    @Test
    @DisplayName("test that create rejects null or blank name and roastery")
    void rejectsBlankNameOrRoastery() {
        assertThrows(IllegalArgumentException.class,
                () -> CoffeeBean.create("  ", "Roastery", defaultBrewSetting(), defaultFlavorProfile()));

        assertThrows(IllegalArgumentException.class,
                () -> CoffeeBean.create("Bean", "   ", defaultBrewSetting(), defaultFlavorProfile()));
    }

    @Test
    @DisplayName("updates BrewSetting atomically by replacing the whole value object")
    void updateBrewSettingsReplacesValueObject() {
        CoffeeBean bean = newBean();
        BrewSetting newSettings = BrewSetting.of(GrindSize.ofSize(2), Dose.ofGrams(20), ExtractionTime.ofSeconds(30));

        bean.updateBrewSetting(newSettings);

        assertEquals(newSettings, bean.getBrewSetting());
    }

    @Test
    @DisplayName("updates FlavorProfile atomically by replacing the whole value object")
    void updateFlavorProfileReplacesValueObject() {
        CoffeeBean bean = newBean();
        FlavorProfile newProfile =  FlavorProfile.of(
                Flavor.CHOCOLATE,
                Intensity.ofValue(4),
                Bitterness.ofValue(3)
        );

        bean.updateFlavorProfile(newProfile);

        assertEquals(newProfile, bean.getFlavorProfile());
    }

    @Test
    @DisplayName("addPackage() adds a new package and returns its reference")
    void addPackageAddsPackageAndReturnsIt() {
        CoffeeBean bean = newBean();
        var pkg = bean.addPackage(Price.ofValue(12.90, testDate), 250, testDate);

        assertNotNull(pkg);
        assertNotNull(pkg.getId());
        assertEquals(1, bean.getPackages().size());
        assertEquals(250, bean.totalRemainingGrams());
    }

    @Test
    @DisplayName("consumeFromPackage() reduces remaining grams and is reflected in totalRemainingGrams()")
    void consumeFromPackageReducesRemaining() {
        CoffeeBean bean = newBean();
        var p1 = bean.addPackage(Price.ofValue(10.00, testDate), 250, testDate);
        var p2 = bean.addPackage(Price.ofValue(9.50, testDate), 250, testDate);

        assertEquals(500, bean.totalRemainingGrams());

        bean.consumeFromPackage(p1.getId(), 30);
        assertEquals(470, bean.totalRemainingGrams());

        bean.consumeFromPackage(p2.getId(), 70);
        assertEquals(400, bean.totalRemainingGrams());
    }

    @Test
    @DisplayName("consumeFromPackage() throws an error if the package does not exist")
    void consumeFromPackageThrowsIfNotFound() {
        CoffeeBean bean = newBean();
        bean.addPackage(Price.ofValue(10.00, testDate), 250, testDate);

        assertThrows(IllegalArgumentException.class,
                () -> bean.consumeFromPackage("does-not-exist", 10));
    }

    @Test
    @DisplayName("consumeFromPackage() throws an error if trying to consume more than remaining grams")
    void consumeFromPackageThrowsIfTooMuch() {
        CoffeeBean bean = newBean();
        var pkg = bean.addPackage(Price.ofValue(10.00, testDate), 100, testDate);

        assertThrows(IllegalArgumentException.class,
                () -> bean.consumeFromPackage(pkg.getId(), 200));
    }

    @Test
    @DisplayName("getPackages() returns an unmodifiable List")
    void packagesIsUnmodifiable() {
        CoffeeBean bean = newBean();
        bean.addPackage(Price.ofValue(10.00, testDate), 250, testDate);

        var list = bean.getPackages();
        assertThrows(UnsupportedOperationException.class, () -> list.add(list.get(0)));
    }
}
