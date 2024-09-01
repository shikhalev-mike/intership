package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Status;

public interface StatusService {
    String NAME = "intership_StatusService";

    void changeStatus(OnlineOrder onlineOrder, String status);
}