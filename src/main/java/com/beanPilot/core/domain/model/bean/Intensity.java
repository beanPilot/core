package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public class Intensity {
    
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    
    private final int value;

    private Intensity(int value) {
        if (value <= MIN_SIZE) {
            throw new IllegalArgumentException("Intensity must be greater than " + MIN_SIZE + ", was: " + value);
        }
        if (value > MAX_SIZE) {
            throw new IllegalArgumentException("Intensity too high (max " + MAX_SIZE + "), was: " + value);
        }
        this.value = value;
    }

    public static Intensity ofValue(int size) {
        return new Intensity(size);
    }

    public int getValue() {
        return value;
    }

    public boolean isGreaterThan(Intensity other) {
        return this.value > other.value;
    }

    public static int getMaxValue() {
        return MAX_SIZE;
    }

    public static int getMinValue() {
        return MIN_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intensity that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "/" + MAX_SIZE;
    }
}
