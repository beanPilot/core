package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.UpdateFlavorProfileCommand;
import com.beanpilot.core.domain.model.bean.*;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UpdateFlavorProfileHandlerTest {

    private final UUID nonExistingUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");


    private BrewSetting brewSettings() {
        return BrewSetting.of(
                GrindSize.ofSize(3),
                Dose.ofGrams(18),
                ExtractionTime.ofSeconds(28)
        );
    }

    private FlavorProfile oldProfile() {
        return FlavorProfile.of(
                Flavor.BERRY,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );
    }

    private FlavorProfile newProfile() {
        return FlavorProfile.of(
                Flavor.CHOCOLATE,
                Intensity.ofValue(4),
                Bitterness.ofValue(3)
        );
    }

    @Test
    void handlerUpdatesFlavorProfileAndSavesAggregate() {
        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);

        CoffeeBean bean = CoffeeBean.create(
                "Test Bean",
                "Roastery",
                brewSettings(),
                oldProfile()
        );

        when(repo.findById(bean.getId()))
                .thenReturn(Optional.of(bean));

        UpdateFlavorProfileHandler handler = new UpdateFlavorProfileHandler(repo);
        UpdateFlavorProfileCommand cmd =
                new UpdateFlavorProfileCommand(bean.getId(), newProfile());

        handler.handle(cmd);

        assertEquals(newProfile(), bean.getFlavorProfile());
        verify(repo, times(1)).save(bean);
    }

    @Test
    void throwsIfBeanNotFound() {
        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);
        when(repo.findById(nonExistingUUID)).thenReturn(Optional.empty());

        UpdateFlavorProfileHandler handler = new UpdateFlavorProfileHandler(repo);
        UpdateFlavorProfileCommand cmd =
                new UpdateFlavorProfileCommand(nonExistingUUID, newProfile());
        assertThrows(IllegalArgumentException.class,
                () -> handler.handle(cmd));
    }
}