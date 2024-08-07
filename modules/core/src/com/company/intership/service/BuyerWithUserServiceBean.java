package com.company.intership.service;

import com.company.intership.entity.Buyer;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service(BuyerWithUserService.NAME)
public class BuyerWithUserServiceBean implements BuyerWithUserService {
    @Inject
    private DataManager dataManager;

    @Override
    public boolean isBuyerWithUserService(User user) {
        Long count = dataManager.loadValue(
                        "select count(b) from intership_Buyer b where b.user.id = :userId", Long.class)
                .parameter("userId", user.getId())
                .one();

        return count > 0;
    }

    @Override
    public Buyer getBuyerByUserId(User user) {
        return dataManager.load(Buyer.class)
                .query("select b from intership_Buyer b where b.user.id = :userId")
                .parameter("userId", user.getId())
                .optional()
                .orElse(null);
    }

}