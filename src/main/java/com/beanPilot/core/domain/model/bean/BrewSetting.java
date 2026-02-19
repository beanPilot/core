package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public final class BrewSetting {

    private final GrindSize grindSize;
    private final Dose dose;
    private final ExtractionTime extractionTime;

    private BrewSetting(GrindSize grindSize, Dose dose, ExtractionTime extractionTime) {
        this.grindSize = Objects.requireNonNull(grindSize, "GrindSize cannot be null");
        this.dose = Objects.requireNonNull(dose, "Dose cannot be null");
        this.extractionTime = Objects.requireNonNull(extractionTime, "ExtractionTime cannot be null ");
    }

    public static BrewSetting of(GrindSize grindSize, Dose dose, ExtractionTime extractionTime) {
        return new BrewSetting(grindSize, dose, extractionTime);
    }

    public GrindSize grindSize() { return grindSize; }
    public Dose dose() { return dose; }
    public ExtractionTime extractionTime() { return extractionTime; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrewSetting that)) return false;
        return this.grindSize.equals(that.grindSize)
            && this.dose.equals(that.dose)
            && this.extractionTime.equals(that.extractionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grindSize, dose, extractionTime);
    }

    @Override
    public String toString() {
        return "BrewSettings[" + grindSize + ", " + dose + ", " + extractionTime + "]";
    }
}
