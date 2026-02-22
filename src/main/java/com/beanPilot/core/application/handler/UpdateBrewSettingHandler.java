package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.UpdateBrewSettingCommand;
import com.beanpilot.core.domain.model.bean.CoffeeBean;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateBrewSettingHandler {

    private final CoffeeBeanRepository repository;

    public UpdateBrewSettingHandler(CoffeeBeanRepository repository) {
        this.repository = repository;
    }

    public void handle(UpdateBrewSettingCommand cmd) {

        CoffeeBean bean = repository.findById(cmd.getCoffeeBeanId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "CoffeeBean not found: " + cmd.getCoffeeBeanId()));

        bean.updateBrewSetting(cmd.getNewSettings());

        repository.save(bean);
    }
}
