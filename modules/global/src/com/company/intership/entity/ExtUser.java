package com.company.intership.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = "intership_ExtUser")
@Extends(User.class)
public class ExtUser extends User {
    private static final long serialVersionUID = 5641185631021970542L;

    @Transient
    @MetaProperty
    private String buyerInfo;

    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo;
    }
}