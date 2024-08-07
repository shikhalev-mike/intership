package com.company.intership.service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ProductService.NAME)
public class ProductServiceBean implements ProductService {
    @Inject
    private DataManager dataManager;

    @Override
    public List<ProductInStore> getRandomProducts(int count) {
        return dataManager.load(ProductInStore.class)
                .query("select e from intership_ProductInStore e order by function('RANDOM')")
                .view("productInStore-view")
                .maxResults(count)
                .list();
    }
}