package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;

public interface UnitsService {
    String NAME = "intership_UnitsService";

    void changeUnitsInStock(OnlineOrder onlineOrder);
}