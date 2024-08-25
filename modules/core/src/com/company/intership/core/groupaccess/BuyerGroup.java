package com.company.intership.core.groupaccess;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = "Buyer")
public class BuyerGroup extends AnnotatedAccessGroupDefinition {
    @JpqlConstraint(target = OnlineOrder.class, where = "{E}.createdBy = :session$userLogin")
    @JpqlConstraint(target = Purchase.class, where = "{E}.createdBy = :session$userLogin")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
