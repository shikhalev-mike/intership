package com.company.intership.service;

import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;

import java.util.List;

public interface ProductService {
    String NAME = "intership_ProductService";

    List<ProductInStore> getRandomProducts(int count);
}