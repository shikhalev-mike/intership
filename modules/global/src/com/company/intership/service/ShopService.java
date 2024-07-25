package com.company.intership.service;

import com.company.intership.entity.CommercialNetwork;
import com.company.intership.entity.Purchase;
import com.company.intership.entity.Shop;

import java.util.List;

public interface ShopService {
    String NAME = "intership_ShopService";

    List<Purchase> getPurchasesByShop(Shop shop);

    List<Purchase> getPurchasesByCommercialNetwork(CommercialNetwork commercialNetwork);
}