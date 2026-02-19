package com.beanpilot.core.domain.model.bean;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Package {

    private final String id;
    private final Price price;

    private final int initialGrams;
    private int remainingGrams;

    private Package(UUID id, Price price, int grams, java.util.Date purchaseDate) {
        this.id = Objects.requireNonNull(id, "id cannot be null").toString();
        this.price = Objects.requireNonNull(price, "price cannot be null");

        if (grams <= 0) throw new IllegalArgumentException("grams must be > 0");
        this.initialGrams = grams;
        this.remainingGrams = grams;
    }

    public static Package create(Price price, int grams, java.util.Date purchaseDate) {
        return new Package(UUID.randomUUID(), price, grams, purchaseDate);
    }

    public String getId() { return id; }
    public Price getPrice() { return price; }
    public int getInitialGrams() { return initialGrams; }
    public int getRemainingGrams() { return remainingGrams; }
    public java.util.Date getPurchaseDate() { return price.getPurchaseDate(); }
    public boolean isEmpty() {
        return remainingGrams == 0;
    }

    public void consume(int grams) {
        if (grams <= 0) throw new IllegalArgumentException("consume must be > 0");
        if (grams > remainingGrams) throw new IllegalArgumentException("not enough grams remaining");
        remainingGrams -= grams;
    }
}
