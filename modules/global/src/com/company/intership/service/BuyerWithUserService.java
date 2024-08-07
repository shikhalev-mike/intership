package com.company.intership.service;

import com.company.intership.entity.Buyer;
import com.haulmont.cuba.security.entity.User;

import java.util.List;
import java.util.Map;

public interface BuyerWithUserService {
    String NAME = "intership_BuyerWithUserService";

    boolean isBuyerWithUserService(User user);

    Buyer getBuyerByUserId(User user);
}