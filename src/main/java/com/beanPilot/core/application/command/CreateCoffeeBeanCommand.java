package com.beanpilot.core.application.command;

import com.beanpilot.core.domain.model.bean.BrewSetting;
import com.beanpilot.core.domain.model.bean.FlavorProfile;
import java.util.Objects;

public final class CreateCoffeeBeanCommand {
    private final String name;
    private final String roastery;
    private final BrewSetting brewSetting;
    private final FlavorProfile flavorProfile;

    public CreateCoffeeBeanCommand(String name, String roastery, BrewSetting brewSetting, FlavorProfile flavorProfile) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.roastery = Objects.requireNonNull(roastery, "roastery cannot be null");
        this.brewSetting = Objects.requireNonNull(brewSetting, "brewSetting cannot be null");
        this.flavorProfile = Objects.requireNonNull(flavorProfile, "flavorProfile cannot be null");
    }

    public String getName() {
        return name;
    }

    public String getRoastery() {
        return roastery;
    }

    public BrewSetting getBrewSetting() {
        return brewSetting;
    }

    public FlavorProfile getFlavorProfile() {
        return flavorProfile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CreateCoffeeBeanCommand that = (CreateCoffeeBeanCommand) obj;
        return Objects.equals(name, that.name) &&
               Objects.equals(roastery, that.roastery) &&
               Objects.equals(brewSetting, that.brewSetting) &&
               Objects.equals(flavorProfile, that.flavorProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roastery, brewSetting, flavorProfile);
    }

    @Override
    public String toString() {
        return "CreateCoffeeBeanCommand{" +
               "name='" + name + '\'' +
               ", roastery='" + roastery + '\'' +
               ", brewSetting=" + brewSetting +
               ", flavorProfile=" + flavorProfile +
               '}';
    }
}
