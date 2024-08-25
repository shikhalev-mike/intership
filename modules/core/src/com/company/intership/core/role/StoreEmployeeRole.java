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

@Role(name = StoreEmployeeRole.NAME)
public class StoreEmployeeRole extends AnnotatedRoleDefinition {
    public final static String NAME = "StoreEmployee";

    @ScreenAccess(screenIds = {"retail-intership", "application-intership", "intership_CommercialNetwork.browse", "intership_Shop.browse", "intership_PriceHistory.browse", "intership_Purchase.browse", "intership_ShopScreen", "intership_ProductWithDiscount.browse", "intership_CommercialNetwork.edit", "intership_OnlineOrder.edit", "intership_ProductInPurchase.edit", "intership_ProductInStore.browse", "intership_ProductInStore.edit", "intership_Purchase.edit", "intership_Shop.edit", "OnlineOrder.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Shop.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = ProductInStore.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Purchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductWithDiscount.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = ProductInPurchase.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = PriceHistory.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = OnlineOrder.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = CommercialNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Buyer.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Shop.class, modify = "productsInStore", view = {"number", "shopType", "address", "name", "commercialNetwork"})
    @EntityAttributeAccess(entityClass = ProductInStore.class, modify = "*")
    @EntityAttributeAccess(entityClass = Purchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductWithDiscount.class, modify = "*")
    @EntityAttributeAccess(entityClass = ProductInPurchase.class, modify = "*")
    @EntityAttributeAccess(entityClass = PriceHistory.class, view = {"product", "shop", "priceChangeDate", "oldPrice", "newPrice"})
    @EntityAttributeAccess(entityClass = OnlineOrder.class, modify = "*")
    @EntityAttributeAccess(entityClass = CommercialNetwork.class, view = {"name", "fullName"})
    @EntityAttributeAccess(entityClass = Buyer.class, view = {"fullName", "address", "email"})
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
