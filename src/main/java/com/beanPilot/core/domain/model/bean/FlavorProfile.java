package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public class FlavorProfile {
    // reference from: https://coffee-mind.com/coffeemind-aroma-wheel/
    private enum Flavor {
        BERRY("Beerig"),
        FRUITY("Fruchtig"),
        DRIED_FRUIT("Getrocknete Früchte"),
        SWEET("Süß"),
        CHOCOLATE("Schokolade"),
        NUTTY("Nussig/Kakao"),
        ROASTED("Geröstet"),
        INDUSTRIAL("Industriell"),
        SPICY("Würzig"),
        CEREAL("Getreide"),
        GREEN("Grün"),
        HERB_VEGETAL("Kräuter/Pflanzlich"),
        FLORAL("Blumig");
        
        private final String displayName;

        Flavor(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final Flavor flavor;
    private final Intensity intensity;
    private final Bitterness bitterness;

    private FlavorProfile(Flavor flavor, Intensity intensity, Bitterness bitterness) {
        this.flavor = flavor;
        this.intensity = intensity;
        this.bitterness = bitterness;
    }

    public static FlavorProfile of(Flavor flavor, Intensity intensity, Bitterness bitterness) {
        return new FlavorProfile(flavor, intensity, bitterness);
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public Bitterness getBitterness() {
        return bitterness;
    }

    @Override
    public String toString() {
        return "FlavorProfile{" +
                "flavor=" + flavor +
                ", intensity=" + intensity +
                ", bitterness=" + bitterness +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlavorProfile that)) return false;
        return flavor == that.flavor && intensity.equals(that.intensity) && bitterness.equals(that.bitterness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavor, intensity, bitterness);
    }
}
