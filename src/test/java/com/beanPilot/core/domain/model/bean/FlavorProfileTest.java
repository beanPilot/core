package com.beanpilot.core.domain.model.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FlavorProfileTest {

    @Test
    @DisplayName("FlavorProfile can be created with valid flavor, intensity, and bitterness")
    void createsValidFlavorProfile() {
        var intensity = Intensity.ofValue(5);
        var bitterness = Bitterness.ofValue(3);
        
        var profile = FlavorProfile.of(
            Flavor.CHOCOLATE, 
            intensity, 
            bitterness
        );

        assertEquals(Flavor.CHOCOLATE, profile.getFlavor());
        assertEquals(intensity, profile.getIntensity());
        assertEquals(bitterness, profile.getBitterness());
    }

    @Test
    @DisplayName("FlavorProfile equality works correctly")
    void testEquality() {
        var intensity1 = Intensity.ofValue(5);
        var bitterness1 = Bitterness.ofValue(3);
        var intensity2 = Intensity.ofValue(5);
        var bitterness2 = Bitterness.ofValue(3);
        
        var profile1 = FlavorProfile.of(Flavor.FRUITY, intensity1, bitterness1);
        var profile2 = FlavorProfile.of(Flavor.FRUITY, intensity2, bitterness2);
        var profile3 = FlavorProfile.of(Flavor.NUTTY, intensity1, bitterness1);

        assertEquals(profile1, profile2);
        assertEquals(profile1.hashCode(), profile2.hashCode());
        
        assertNotEquals(profile1, profile3);
        
        assertEquals(profile1, profile1);
        
        assertNotEquals(profile1, null);
    }

    @Test
    @DisplayName("FlavorProfile with different intensity should not be equal")
    void testInequalityWithDifferentIntensity() {
        var intensity1 = Intensity.ofValue(5);
        var intensity2 = Intensity.ofValue(7);
        var bitterness = Bitterness.ofValue(3);
        
        var profile1 = FlavorProfile.of(Flavor.SWEET, intensity1, bitterness);
        var profile2 = FlavorProfile.of(Flavor.SWEET, intensity2, bitterness);

        assertNotEquals(profile1, profile2);
    }

    @Test
    @DisplayName("FlavorProfile with different bitterness should not be equal")
    void testInequalityWithDifferentBitterness() {
        var intensity = Intensity.ofValue(5);
        var bitterness1 = Bitterness.ofValue(3);
        var bitterness2 = Bitterness.ofValue(8);
        
        var profile1 = FlavorProfile.of(Flavor.ROASTED, intensity, bitterness1);
        var profile2 = FlavorProfile.of(Flavor.ROASTED, intensity, bitterness2);

        assertNotEquals(profile1, profile2);
    }

    @Test
    @DisplayName("FlavorProfile toString contains all properties")
    void testToString() {
        var intensity = Intensity.ofValue(6);
        var bitterness = Bitterness.ofValue(4);
        var profile = FlavorProfile.of(Flavor.BERRY, intensity, bitterness);

        String result = profile.toString();
        
        assertTrue(result.contains("FlavorProfile"));
        assertTrue(result.contains("BERRY"));
        assertTrue(result.contains(intensity.toString()));
        assertTrue(result.contains(bitterness.toString()));
    }

    @Test
    @DisplayName("All flavor enum values have display names")
    void testFlavorDisplayNames() {
        assertEquals("Beerig", Flavor.BERRY.getDisplayName());
        assertEquals("Schokolade", Flavor.CHOCOLATE.getDisplayName());
        assertEquals("Fruchtig", Flavor.FRUITY.getDisplayName());
        assertEquals("Süß", Flavor.SWEET.getDisplayName());
        assertEquals("Blumig", Flavor.FLORAL.getDisplayName());
    }

    @Test
    @DisplayName("FlavorProfile can be created with all flavor types")
    void testAllFlavorTypes() {
        var intensity = Intensity.ofValue(5);
        var bitterness = Bitterness.ofValue(5);

        for (Flavor flavor : Flavor.values()) {
            var profile = FlavorProfile.of(flavor, intensity, bitterness);
            assertEquals(flavor, profile.getFlavor());
            assertNotNull(flavor.getDisplayName());
            assertFalse(flavor.getDisplayName().isEmpty());
        }
    }

    @Test
    @DisplayName("FlavorProfile requires non-null parameters")
    void testNullParameterHandling() {
        var intensity = Intensity.ofValue(5);
        var bitterness = Bitterness.ofValue(5);

        assertThrows(NullPointerException.class, () -> 
            FlavorProfile.of(null, intensity, bitterness));
        
        assertThrows(NullPointerException.class, () -> 
            FlavorProfile.of(Flavor.CHOCOLATE, null, bitterness));
        
        assertThrows(NullPointerException.class, () -> 
            FlavorProfile.of(Flavor.CHOCOLATE, intensity, null));
    }
}
