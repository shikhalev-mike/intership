package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;

public interface RollbackService {
    String NAME = "intership_RollbackService";

    void rollback(OnlineOrder onlineOrder);
}