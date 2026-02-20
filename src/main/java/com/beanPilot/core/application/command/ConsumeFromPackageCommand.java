package com.beanpilot.core.application.command;

import java.util.Objects;
import java.util.UUID;

public final class ConsumeFromPackageCommand {

    private final UUID coffeeBeanId;
    private final UUID packageId;
    private final int grams;

    public ConsumeFromPackageCommand(UUID coffeeBeanId, UUID packageId, int grams) {
        if (coffeeBeanId == null) {
            throw new IllegalArgumentException("coffeeBeanId must not be null");
        }
        if (packageId == null) {
            throw new IllegalArgumentException("packageId must not be null");
        }
        if (grams <= 0) {
            throw new IllegalArgumentException("grams must be > 0");
        }

        this.coffeeBeanId = coffeeBeanId;
        this.packageId = packageId;
        this.grams = grams;
    }

    public UUID getCoffeeBeanId() {
        return coffeeBeanId;
    }

    public UUID getPackageId() {
        return packageId;
    }

    public int getGrams() {
        return grams;
    }


    @Override    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumeFromPackageCommand other)) return false;
        return grams == other.grams
                && coffeeBeanId.equals(other.coffeeBeanId)
                && packageId.equals(other.packageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeBeanId, packageId, grams);
    }

    @Override
    public String toString() {
        return "ConsumeFromPackageCommand[" +
                "coffeeBeanId='" + coffeeBeanId + '\'' +
                ", packageId='" + packageId + '\'' +
                ", grams=" + grams +
                ']';
    }
}
