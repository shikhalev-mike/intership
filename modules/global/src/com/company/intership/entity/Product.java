package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "INTERSHIP_PRODUCT")
@Entity(name = "intership_Product")
@NamePattern("%s|name")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 5041050508644257392L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "MANUFACTURER_PRICE", nullable = false)
    private BigDecimal manufacturerPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MANUFACTURER_ID")
    private ManufacturerOfGoods manufacturer;

    public BigDecimal getManufacturerPrice() {
        return manufacturerPrice;
    }

    public void setManufacturerPrice(BigDecimal manufacturerPrice) {
        this.manufacturerPrice = manufacturerPrice;
    }

    public ManufacturerOfGoods getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerOfGoods manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}