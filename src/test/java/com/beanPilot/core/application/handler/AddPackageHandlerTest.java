package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.AddPackageCommand;
import com.beanpilot.core.domain.model.bean.*;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AddPackageHandlerTest {
    private final int testYear = 2026;
    private final int testMonth = 2;
    private final int testDay = 19;
    private final java.util.Date testDate = Date.from(LocalDate.of(testYear, testMonth, testDay).atStartOfDay(ZoneId.systemDefault()).toInstant());
    private final UUID nonExistingUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");


    private BrewSetting brewSettings() {
        return BrewSetting.of(
                GrindSize.ofSize(3),
                Dose.ofGrams(18),
                ExtractionTime.ofSeconds(28));
    }

    private FlavorProfile flavorProfile() {
        return  FlavorProfile.of(
                Flavor.HERB_VEGETAL,
                Intensity.ofValue(3),
                Bitterness.ofValue(2)
        );
    }

    @Test
    void handlerAddsPackageAndSavesAggregate() {

        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);

        CoffeeBean bean = CoffeeBean.create(
                "Test Bean", "Test Roastery",
                brewSettings(), flavorProfile()
        );

        when(repo.findById(bean.getId()))
                .thenReturn(Optional.of(bean));

        AddPackageHandler handler = new AddPackageHandler(repo);

        AddPackageCommand cmd = new AddPackageCommand(
                bean.getId(),
                Price.ofValue(12.50, testDate),
                250
        );

        UUID packageId = handler.handle(cmd);

        assertNotNull(packageId);
        assertEquals(250, bean.totalRemainingGrams());
        verify(repo, times(1)).save(bean);
    }

    @Test
    void handlerThrowsIfBeanNotFound() {
        CoffeeBeanRepository repo = mock(CoffeeBeanRepository.class);
        when(repo.findById(nonExistingUUID)).thenReturn(Optional.empty());

        AddPackageHandler handler = new AddPackageHandler(repo);

        AddPackageCommand cmd =
                new AddPackageCommand(nonExistingUUID, Price.ofValue(10, testDate), 100);

        assertThrows(IllegalArgumentException.class, () -> handler.handle(cmd));
    }
}