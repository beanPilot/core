package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.ConsumeFromPackageCommand;
import com.beanpilot.core.domain.model.bean.CoffeeBean;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumeFromPackageHandler {

    private final CoffeeBeanRepository repository;

    public ConsumeFromPackageHandler(CoffeeBeanRepository repository) {
        this.repository = repository;
    }

    public void handle(ConsumeFromPackageCommand cmd) {

        CoffeeBean bean = repository.findById(cmd.getCoffeeBeanId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "CoffeeBean not found: " + cmd.getCoffeeBeanId()));

        bean.consumeFromPackage(cmd.getPackageId(), cmd.getGrams());

        repository.save(bean);
    }
}
