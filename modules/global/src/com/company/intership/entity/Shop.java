package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "INTERSHIP_SHOP")
@Entity(name = "intership_Shop")
@NamePattern("%s %s|name,number")
public class Shop extends StandardEntity {
    private static final long serialVersionUID = -5757899352020371634L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    private String number;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "shop")
    private List<ProductInStore> productsInStore;

    @Column(name = "SHOP_TYPE")
    private String shopType;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "house", column = @Column(name = "ADDRESS_HOUSE"))
    })
    private Address address;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMMERCIAL_NETWORK_ID")
    private CommercialNetwork commercialNetwork;

    public ShopType getShopType() {
        return shopType == null ? null : ShopType.fromId(shopType);
    }

    public void setShopType(ShopType shopType) {
        this.shopType = shopType == null ? null : shopType.getId();
    }

    public List<ProductInStore> getProductsInStore() {
        return productsInStore;
    }

    public void setProductsInStore(List<ProductInStore> productsInStore) {
        this.productsInStore = productsInStore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CommercialNetwork getCommercialNetwork() {
        return commercialNetwork;
    }

    public void setCommercialNetwork(CommercialNetwork commercialNetwork) {
        this.commercialNetwork = commercialNetwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}