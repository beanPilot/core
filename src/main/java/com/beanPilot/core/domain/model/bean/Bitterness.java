package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public class Bitterness {
    
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    
    private final int value;

    private Bitterness(int value) {
        if (value <= MIN_SIZE) {
            throw new IllegalArgumentException("Bitterness must be greater than " + MIN_SIZE + ", was: " + value);
        }
        if (value > MAX_SIZE) {
            throw new IllegalArgumentException("Bitterness too high (max " + MAX_SIZE + "), was: " + value);
        }
        this.value = value;
    }

    public static Bitterness ofValue(int size) {
        return new Bitterness(size);
    }

    public int getValue() {
        return value;
    }

    public boolean isGreaterThan(Bitterness other) {
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
        if (!(o instanceof Bitterness that)) return false;
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
