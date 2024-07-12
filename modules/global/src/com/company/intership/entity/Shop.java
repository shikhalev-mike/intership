package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "INTERSHIP_SHOP")
@Entity(name = "intership_Shop")
@NamePattern("%s %s|name,number")
public class Shop extends StandardEntity {
    private static final long serialVersionUID = -5757899352020371634L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    private String number;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMMERCIAL_NETWORK_ID")
    private CommercialNetwork commercialNetwork;

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