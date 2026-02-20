package com.beanpilot.core.domain.repository;

import com.beanpilot.core.domain.model.bean.CoffeeBean;
import java.util.*;

public interface CoffeeBeanRepository {

    void save(CoffeeBean bean);

    Optional<CoffeeBean> findById(UUID id);

    List<CoffeeBean> findAll();

    List<CoffeeBean> findByRoastery(String roastery);

}