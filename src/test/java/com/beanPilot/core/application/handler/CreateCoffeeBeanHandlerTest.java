package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.CreateCoffeeBeanCommand;
import com.beanpilot.core.domain.model.bean.*;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateCoffeeBeanHandlerTest {

    @Test
    void handleCreatesAndSavesCoffeeBean() {

        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);
        CreateCoffeeBeanHandler handler = new CreateCoffeeBeanHandler(repo);

        BrewSetting settings = BrewSetting.of(
                GrindSize.ofSize(3),
                Dose.ofGrams(18),
                ExtractionTime.ofSeconds(28)
        );

        FlavorProfile profile = FlavorProfile.of(
                Flavor.CHOCOLATE,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );

        CreateCoffeeBeanCommand cmd = new CreateCoffeeBeanCommand(
                "Ethiopia Guji",
                "Acme Roastery",
                settings,
                profile
        );

        UUID id = handler.handle(cmd);

        assertNotNull(id);
        verify(repo, times(1)).save(any(CoffeeBean.class));
    }
}
