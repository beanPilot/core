package com.beanpilot.core.application.command;

import com.beanpilot.core.domain.model.bean.BrewSetting;
import java.util.Objects;
import java.util.UUID;

public class UpdateBrewSettingCommand {

    private final UUID coffeeBeanId;
    private final BrewSetting newSettings;

    public UpdateBrewSettingCommand(UUID coffeeBeanId, BrewSetting newSettings) {
        Objects.requireNonNull(coffeeBeanId, "coffeeBeanId must not be null");
        this.coffeeBeanId = coffeeBeanId;
        this.newSettings = Objects.requireNonNull(newSettings, "newSettings");
    }

    public UUID getCoffeeBeanId() {
        return coffeeBeanId;
    }

    public BrewSetting getNewSettings() {
        return newSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateBrewSettingCommand other)) return false;
        return coffeeBeanId.equals(other.coffeeBeanId)
                && newSettings.equals(other.newSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeBeanId, newSettings);
    }

    @Override
    public String toString() {
        return "UpdateBrewSettingsCommand[" +
                "coffeeBeanId='" + coffeeBeanId + '\'' +
                ", newSettings=" + newSettings +
                ']';
    }
}
