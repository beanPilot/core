package com.beanpilot.core.application.command;

import java.util.Objects;
import java.util.UUID;

import com.beanpilot.core.domain.model.bean.Price;

public class AddPackageCommand {

    private final UUID coffeeBeanId;
    private final Price price;
    private final int grams;

    public AddPackageCommand(UUID coffeeBeanId,
                             Price price,
                             int grams) {

        if (coffeeBeanId == null)
            throw new IllegalArgumentException("coffeeBeanId must not be null");

        if (price == null)
            throw new IllegalArgumentException("priceAmount must not be null");

        if (grams <= 0)
            throw new IllegalArgumentException("grams must be > 0");

        this.coffeeBeanId = coffeeBeanId;
        this.price = price;
        this.grams = grams;
    }

    public UUID getCoffeeBeanId() {
        return coffeeBeanId;
    }

    public double getPrice() {
        return price.getPrice();
    }

    public int getGrams() {
        return grams;
    }

    public java.util.Date getPurchaseDate() {
        return price.getPurchaseDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddPackageCommand other)) return false;
        return Double.compare(other.price.getPrice(), price.getPrice()) == 0
                && grams == other.grams
                && coffeeBeanId.equals(other.coffeeBeanId)
                && price.getPurchaseDate().equals(other.price.getPurchaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeBeanId, price, grams);
    }

    @Override
    public String toString() {
        return "AddPackageCommand[" +
                "coffeeBeanId='" + coffeeBeanId + '\'' +
                ", price=" + price.getPrice() +
                ", grams=" + grams +
                ", purchaseDate=" + price.getPurchaseDate() +
                ']';
    }
}
