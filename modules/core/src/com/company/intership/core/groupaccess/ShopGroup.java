package com.company.intership.core.groupaccess;

import com.company.intership.entity.*;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

import javax.inject.Inject;

@AccessGroup(name = "Shop")
public class ShopGroup extends AnnotatedAccessGroupDefinition {
    @Inject
    UserSessionSource userSessionSource;

    @JpqlConstraint(target = CommercialNetwork.class, where = "{E}.id in (\n" +
            "    select s.commercialNetwork.id\n" +
            "    from intership_Shop s\n" +
            "    join s.employees e\n" +
            "    where e.user.id = :session$userId\n" +
            ")")
    @JpqlConstraint(target = PriceHistory.class, join = "{E}.shop.employees s", where = "s.user.id = :session$userId")
    @JpqlConstraint(target = Shop.class, join = "{E}.employees q", where = "q.user.id = :session$userId")
    @JpqlConstraint(target = ProductInStore.class, join = "{E}.shop.employees s", where = "s.user.id = :session$userId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
