package com.company.intership.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;

@MetaClass(name = "intership_ProductWithDiscount")
public class ProductWithDiscount extends BaseUuidEntity {
    private static final long serialVersionUID = 3949452380734116559L;

    @MetaProperty
    private Product product;

    @MetaProperty
    private Shop shop;

    @MetaProperty
    private BigDecimal price;

    @MetaProperty
    private BigDecimal priceWithDiscount;

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}