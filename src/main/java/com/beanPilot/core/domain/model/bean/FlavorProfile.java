package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public final class FlavorProfile {
    
    public static final String Flavor = null;
    private final Flavor flavor;
    private final Intensity intensity;
    private final Bitterness bitterness;

    private FlavorProfile(Flavor flavor, Intensity intensity, Bitterness bitterness) {
        this.flavor = Objects.requireNonNull(flavor, "flavor cannot be null");
        this.intensity = Objects.requireNonNull(intensity, "intensity cannot be null");
        this.bitterness = Objects.requireNonNull(bitterness, "bitterness cannot be null");
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
