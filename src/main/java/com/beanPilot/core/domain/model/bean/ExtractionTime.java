package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public final class ExtractionTime {
    
    private final int seconds;

    private ExtractionTime(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Extraction time must be positive, was: " + seconds);
        }
        this.seconds = seconds;
    }

    public static ExtractionTime ofSeconds(int seconds) {
        return new ExtractionTime(seconds);
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isGreaterThan(ExtractionTime other) {
        return this.seconds > other.seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtractionTime that)) return false;
        return seconds == that.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seconds);
    }

    @Override
    public String toString() {
        return seconds + "s";
    }
}
