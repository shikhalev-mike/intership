package com.company.intership.core.role;

import com.company.intership.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.reports.entity.charts.AbstractChartDescription;

@Role(name = BuyerRole.NAME)
public class BuyerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Buyer";

    @EntityAccess(entityClass = Product.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Buyer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = ProductInStore.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = ProductWithDiscount.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Shop.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductInPurchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Product.class, view = {"name", "manufacturer"})
    @EntityAttributeAccess(entityClass = Buyer.class, view = "fullName")
    @EntityAttributeAccess(entityClass = Shop.class, view = {"name", "address"})
    @EntityAttributeAccess(entityClass = Address.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductInStore.class, view = "*")
    @EntityAttributeAccess(entityClass = ProductWithDiscount.class, view = "*")
    @EntityAttributeAccess(entityName = "sec$AbstractConditionDescriptor", view = "*")
    @EntityAttributeAccess(entityName = "sec$AbstractCondition", view = "*")
    @EntityAttributeAccess(entityClass = AbstractChartDescription.class, view = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"application-intership", "intership_Purchase.browse", "intership_ProductWithDiscount.browse", "intership_OnlineOrder.edit", "intership_ProductInPurchase.edit", "intership_ProductInStore.browse", "intership_Purchase.edit", "OnlineOrder.browse", "intership_Shop.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
