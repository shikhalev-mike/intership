package com.company.intership.core;

import com.company.intership.entity.Purchase;
import com.company.intership.entity.Shop;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(PurchasesInStoreBean.NAME)
public class PurchasesInStoreBean {
    public static final String NAME = "intership_PurchasesInStoreBean";
    private final DataManager dataManager;

    @Inject
    public PurchasesInStoreBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Purchase> getPurchasesByShop(Shop shop) {
        return dataManager.load(Purchase.class)
                .query("select p from intership_Purchase p where p.shop.id = :shopId")
                .parameter("shopId", shop.getId())
                .view("purchase-view")
                .list();
    }
}