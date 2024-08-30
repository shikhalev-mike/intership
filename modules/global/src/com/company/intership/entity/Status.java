package com.company.intership.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Status implements EnumClass<String> {

    NEW("NEW"),
    ACCEPTED_AND_CONFIRMED("Accepted and confirmed"),
    PAID_AND_COMPLETED("Paid and completed"),
    CANCELLED("Cancelled");

    private String id;

    Status(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Status fromId(String id) {
        for (Status at : Status.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}