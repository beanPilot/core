package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.UpdateBrewSettingCommand;
import com.beanpilot.core.domain.model.bean.*;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UpdateBrewSettingHandlerTest {

    private final UUID nonExistingUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private BrewSetting oldSettings() {
        return BrewSetting.of(
                GrindSize.ofSize(3),
                Dose.ofGrams(18),
                ExtractionTime.ofSeconds(28)
        );
    }

    private BrewSetting newSettings() {
        return BrewSetting.of(
                GrindSize.ofSize(1),
                Dose.ofGrams(20),
                ExtractionTime.ofSeconds(30)
        );
    }

    private FlavorProfile profile() {
        return FlavorProfile.of(
                Flavor.BERRY,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );
    }

    @Test
    void handlerUpdatesBrewSettingsAndSavesAggregate() {
        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);

        CoffeeBean bean = CoffeeBean.create(
                "Test Bean",
                "Roastery",
                oldSettings(),
                profile()
        );

        when(repo.findById(bean.getId()))
                .thenReturn(Optional.of(bean));

        UpdateBrewSettingHandler handler =
                new UpdateBrewSettingHandler(repo);
        UpdateBrewSettingCommand cmd =
                new UpdateBrewSettingCommand(bean.getId(), newSettings());

        handler.handle(cmd);

        assertEquals(newSettings(), bean.getBrewSetting());
        verify(repo, times(1)).save(bean);
    }

    @Test
    void throwsIfBeanNotFound() {
        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);
        when(repo.findById(nonExistingUUID)).thenReturn(Optional.empty());

        UpdateBrewSettingHandler handler = new UpdateBrewSettingHandler(repo);

        UpdateBrewSettingCommand cmd =
                new UpdateBrewSettingCommand(nonExistingUUID, newSettings());

        assertThrows(IllegalArgumentException.class,
                () -> handler.handle(cmd));
    }
}
