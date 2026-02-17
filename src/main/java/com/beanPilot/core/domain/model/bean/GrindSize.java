package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public class GrindSize {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    
    private final int size;

    private GrindSize(int size) {
        if (size <= MIN_SIZE) {
            throw new IllegalArgumentException("Grind size must be greater than " + MIN_SIZE + ", was: " + size);
        }
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("Grind size too high (max " + MAX_SIZE + "), was: " + size);
        }
        this.size = size;
    }

    public static GrindSize ofSize(int size) {
        return new GrindSize(size);
    }

    public int getSize() {
        return size;
    }

    public boolean isGreaterThan(GrindSize other) {
        return this.size > other.size;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public int getMinSize() {
        return MIN_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrindSize that)) return false;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return size + "g";
    }
}
