package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public final class Dose {

    private final int gram;

    private Dose(int gram) {
        if (gram <= 0) {
            throw new IllegalArgumentException("Grinded dose must be greater than 0g, was: " + gram);
        }
        if (gram > 100) {
            // do not expect more than 100g of grinded coffee, as this would be a very large batch
            throw new IllegalArgumentException("Grinded dose too high (max 100g), was: " + gram);
        }
        this.gram = gram;
    }

    public static Dose ofgram(int gram) {
        return new Dose(gram);
    }

    public int ingram() {
        return gram;
    }

    public boolean isGreaterThan(Dose other) {
        return this.gram > other.gram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dose that)) return false;
        return gram == that.gram;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gram);
    }

    @Override
    public String toString() {
        return gram + "g";
    }
}