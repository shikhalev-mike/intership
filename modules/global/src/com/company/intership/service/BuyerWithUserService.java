package com.company.intership.service;

import com.company.intership.entity.Buyer;
import com.haulmont.cuba.security.entity.User;
import jdk.internal.jline.internal.Nullable;

import java.util.List;
import java.util.Map;

public interface BuyerWithUserService {
    String NAME = "intership_BuyerWithUserService";

    @Nullable
    boolean isBuyerWithUserService(User user);

    @Nullable
    Buyer getBuyerByUserId(User user);
}