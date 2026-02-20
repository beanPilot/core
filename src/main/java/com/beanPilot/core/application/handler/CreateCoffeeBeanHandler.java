package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.CreateCoffeeBeanCommand;
import com.beanpilot.core.domain.model.bean.CoffeeBean;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CreateCoffeeBeanHandler {

    private final CoffeeBeanRepository repository;

    public CreateCoffeeBeanHandler(CoffeeBeanRepository repository) {
        this.repository = repository;
    }

    public UUID handle(CreateCoffeeBeanCommand cmd) {

        CoffeeBean bean = CoffeeBean.create(
                cmd.getName(),
                cmd.getRoastery(),
                cmd.getBrewSetting(),
                cmd.getFlavorProfile()
        );

        repository.save(bean);

        return bean.getId();
    }
}
