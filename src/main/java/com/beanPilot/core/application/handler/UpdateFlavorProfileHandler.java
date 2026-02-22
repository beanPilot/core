package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.UpdateFlavorProfileCommand;
import com.beanpilot.core.domain.model.bean.CoffeeBean;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateFlavorProfileHandler {

    private final CoffeeBeanRepository repository;

    public UpdateFlavorProfileHandler(CoffeeBeanRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateFlavorProfileCommand cmd) {

        CoffeeBean bean = repository.findById(cmd.getCoffeeBeanId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "CoffeeBean not found: " + cmd.getCoffeeBeanId()));

        bean.updateFlavorProfile(cmd.getNewFlavorProfile());

        repository.save(bean);
    }
}
