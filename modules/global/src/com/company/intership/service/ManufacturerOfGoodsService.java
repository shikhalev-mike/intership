package com.company.intership.service;

import com.company.intership.entity.ManufacturerOfGoods;
import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Shop;

import java.util.List;

public interface ManufacturerOfGoodsService {
    String NAME = "intership_ManufacturerOfGoodsService";

    List<ProductInStore> getProductsByManufacturerInStore(ManufacturerOfGoods manufacturerOfGoods, Shop shop
            , int maxQuantity);

    List<ProductInStore> getProductsByManufacturerInAllStores(ManufacturerOfGoods manufacturerOfGoods, int maxQuantity);

    List<Shop> getStoresWithoutProduct(Product product);
}