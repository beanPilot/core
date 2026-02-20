package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.ConsumeFromPackageCommand;
import com.beanpilot.core.domain.model.bean.*;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsumeFromPackageHandlerTest {
    private final int testYear = 2024;
    private final int testMonth = 2;
    private final int testDay = 19;
    private final java.util.Date testDate = Date.from(LocalDate.of(testYear, testMonth, testDay).atStartOfDay(ZoneId.systemDefault()).toInstant());
    private final UUID nonExistingUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");


    private BrewSetting brewSettings() {
        return BrewSetting.of(
                GrindSize.ofSize(4),
                Dose.ofGrams(18),
                ExtractionTime.ofSeconds(28)
        );
    }

    private FlavorProfile flavorProfile() {
        return FlavorProfile.of(
                Flavor.BERRY,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );
    }

    @Test
    void handlerConsumesFromPackageAndSavesAggregate() {
        CoffeeBeanRepository repository = mock(CoffeeBeanRepository.class);

        CoffeeBean bean = CoffeeBean.create(
                "Test Bean",
                "Acme Roastery",
                brewSettings(),
                flavorProfile()
        );

        var pkg = bean.addPackage(Price.ofValue(12.50, testDate), 250, testDate);

        when(repository.findById(bean.getId()))
                .thenReturn(java.util.Optional.of(bean));

        ConsumeFromPackageHandler handler =
                new ConsumeFromPackageHandler(repository);

        ConsumeFromPackageCommand cmd =
                new ConsumeFromPackageCommand(bean.getId(), pkg.getId(), 50);

        handler.handle(cmd);

        assertEquals(200, bean.totalRemainingGrams());
        verify(repository, times(1)).save(bean);
    }

    @Test
    void throwsIfCoffeeBeanNotFound() {
        CoffeeBeanRepository repository = mock(CoffeeBeanRepository.class);

        when(repository.findById(nonExistingUUID))
                .thenReturn(java.util.Optional.empty());

        ConsumeFromPackageHandler handler =
                new ConsumeFromPackageHandler(repository);

        ConsumeFromPackageCommand cmd =
                new ConsumeFromPackageCommand(nonExistingUUID, nonExistingUUID, 10);

        assertThrows(IllegalArgumentException.class,
                () -> handler.handle(cmd));
    }
}
