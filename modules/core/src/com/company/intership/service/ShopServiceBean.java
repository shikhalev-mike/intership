package com.company.intership.service;

import com.company.intership.core.PurchasesInStoreBean;
import com.company.intership.entity.CommercialNetwork;
import com.company.intership.entity.Purchase;
import com.company.intership.entity.Shop;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service(ShopService.NAME)
public class ShopServiceBean implements ShopService {
    private final DataManager dataManager;
    private final PurchasesInStoreBean purchasesInStoreBean;

    @Inject
    public ShopServiceBean(DataManager dataManager, PurchasesInStoreBean purchasesInStoreBean) {
        this.dataManager = dataManager;
        this.purchasesInStoreBean = purchasesInStoreBean;
    }

    @Override
    public List<Purchase> getPurchasesByShop(Shop shop) {
        return purchasesInStoreBean.getPurchasesByShop(shop);
    }

    @Override
    public List<Purchase> getPurchasesByCommercialNetwork(CommercialNetwork commercialNetwork) {
        List<Shop> shops = dataManager.load(Shop.class)
                .query("select s from intership_Shop s where s.commercialNetwork.id = :commercialNetworkId")
                .parameter("commercialNetworkId", commercialNetwork.getId())
                .view("shop-view")
                .list();

        List<Purchase> allPurchases = new ArrayList<>();
        for (Shop shop : shops) {
            allPurchases.addAll(purchasesInStoreBean.getPurchasesByShop(shop));
        }
        return allPurchases;
    }
}