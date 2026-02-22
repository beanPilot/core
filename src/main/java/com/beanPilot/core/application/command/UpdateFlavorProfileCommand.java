package com.beanpilot.core.application.command;

import com.beanpilot.core.domain.model.bean.FlavorProfile;

import java.util.Objects;
import java.util.UUID;

public class UpdateFlavorProfileCommand {

    private final UUID coffeeBeanId;
    private final FlavorProfile newFlavorProfile;

    public UpdateFlavorProfileCommand(UUID coffeeBeanId, FlavorProfile newFlavorProfile) {
        Objects.requireNonNull(coffeeBeanId, "coffeeBeanId must not be null");
        this.coffeeBeanId = coffeeBeanId;
        this.newFlavorProfile = Objects.requireNonNull(newFlavorProfile, "newFlavorProfile");
    }

    public UUID getCoffeeBeanId() {
        return coffeeBeanId;
    }

    public FlavorProfile getNewFlavorProfile() {
        return newFlavorProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateFlavorProfileCommand other)) return false;
        return coffeeBeanId.equals(other.coffeeBeanId)
                && newFlavorProfile.equals(other.newFlavorProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeBeanId, newFlavorProfile);
    }

    @Override
    public String toString() {
        return "UpdateFlavorProfileCommand[" +
                "coffeeBeanId='" + coffeeBeanId + '\'' +
                ", newFlavorProfile=" + newFlavorProfile +
                ']';
    }
}
