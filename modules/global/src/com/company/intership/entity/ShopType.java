package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ShopType implements EnumClass<String> {

    AT_THE_HOUSE("At the house"),
    SUPERMARKET("SUPERMARKET"),
    HYPERMARKET("HYPERMARKET");

    private String id;

    ShopType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ShopType fromId(String id) {
        for (ShopType at : ShopType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}