package com.company.intership.core.groupaccess;

import com.company.intership.entity.ManufacturerOfGoods;
import com.company.intership.entity.Product;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name = "Manufacturer")
public class ManufacturerGroup extends AnnotatedAccessGroupDefinition {
    @JpqlConstraint(target = ManufacturerOfGoods.class, where = "{E}.user.id = :session$userId")
    @JpqlConstraint(target = Product.class, where = "{E}.manufacturer.user.id = :session$userId")
    @JpqlConstraint(target = ProductInStore.class, where = "{E}.product.manufacturer.user.id = :session$userId")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
