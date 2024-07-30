package com.company.intership.service;

import com.company.intership.entity.ManufacturerOfGoods;
import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Shop;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ManufacturerOfGoodsService.NAME)
public class ManufacturerOfGoodsServiceBean implements ManufacturerOfGoodsService {
    private final DataManager dataManager;

    @Inject
    public ManufacturerOfGoodsServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public List<ProductInStore> getProductsByManufacturerInStore(ManufacturerOfGoods manufacturerOfGoods, Shop shop
            , int maxQuantity) {
        return dataManager.load(ProductInStore.class)
                .query("select pis from intership_ProductInStore pis " +
                        "join pis.product p " +
                        "where p.manufacturer.id = :manufacturerId " +
                        "and pis.shop.id = :shopId " +
                        "and (pis.quantity <= :maxQuantity or pis.quantity = 0)")
                .parameter("manufacturerId", manufacturerOfGoods.getId())
                .parameter("shopId", shop.getId())
                .parameter("maxQuantity", maxQuantity)
                .view("productInStore-view")
                .list();
    }

    @Override
    public List<ProductInStore> getProductsByManufacturerInAllStores(ManufacturerOfGoods manufacturerOfGoods
            , int maxQuantity) {
        return dataManager.load(ProductInStore.class)
                .query("select pis from intership_ProductInStore pis " +
                        "join pis.product p " +
                        "where p.manufacturer.id = :manufacturerId " +
                        "and (pis.quantity <= :maxQuantity or pis.quantity = 0)")
                .parameter("manufacturerId", manufacturerOfGoods.getId())
                .parameter("maxQuantity", maxQuantity)
                .view("productInStore-view")
                .list();
    }

    @Override
    public List<Shop> getStoresWithoutProduct(Product product) {
        return dataManager.load(Shop.class)
                .query("select s from intership_Shop s where s.id not in " +
                        "(select pis.shop.id from intership_ProductInStore pis where pis.product.id = :productId " +
                        "and pis.quantity > 0)")
                .parameter("productId", product.getId())
                .view("shop-view")
                .list();
    }
}