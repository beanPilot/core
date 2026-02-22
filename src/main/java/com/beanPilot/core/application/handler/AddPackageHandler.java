package com.beanpilot.core.application.handler;

import com.beanpilot.core.application.command.AddPackageCommand;
import com.beanpilot.core.domain.model.bean.CoffeeBean;
import com.beanpilot.core.domain.model.bean.Price;
import com.beanpilot.core.domain.repository.CoffeeBeanRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AddPackageHandler {

    private final CoffeeBeanRepository repository;

    public AddPackageHandler(CoffeeBeanRepository repository) {
        this.repository = repository;
    }

    public UUID handle(AddPackageCommand cmd) {

        CoffeeBean bean = repository.findById(cmd.getCoffeeBeanId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "CoffeeBean not found: " + cmd.getCoffeeBeanId()
                ));

        var newPackage = bean.addPackage(
                Price.ofValue(cmd.getPrice(), cmd.getPurchaseDate()),
                cmd.getGrams(),
                cmd.getPurchaseDate()
        );

        repository.save(bean);

        return newPackage.getId();
    }
}
