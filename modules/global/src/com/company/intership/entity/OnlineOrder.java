package com.company.intership.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.DbView;

import javax.persistence.*;
import java.math.BigDecimal;

@DbView
@Entity(name = "intership_OnlineOrder")
@PublishEntityChangedEvents
@NamePattern("%s|number")
public class OnlineOrder extends Purchase {
    private static final long serialVersionUID = 1919316582231062618L;

    @Column(name = "NUMBER_")
    private String number;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    @Transient
    @MetaProperty
    private BigDecimal orderAmount;

    @Column(name = "DISCOUNT")
    private Integer discount;

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}