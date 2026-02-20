package com.beanpilot.core.domain.model.bean;

import java.util.Objects;

public final class Price {
    
    private final double price;
    private final java.util.Date purchaseDate;

    private Price(double price, java.util.Date purchaseDate) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0, was: " + price);
        }
        this.price = price;
        this.purchaseDate = Objects.requireNonNull(purchaseDate, "purchaseDate cannot be null");
    }

    public static Price ofValue(double price, java.util.Date purchaseDate) {
        return new Price(price, purchaseDate);
    }

    public double getPrice() {
        return price;
    }

    public java.util.Date getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isGreaterThan(Price other) {
        return this.price > other.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price that)) return false;
        return Double.compare(that.price, price) == 0 &&
               Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, purchaseDate);
    }

    @Override
    public String toString() {
        return price + " on " + purchaseDate;
    }
}

