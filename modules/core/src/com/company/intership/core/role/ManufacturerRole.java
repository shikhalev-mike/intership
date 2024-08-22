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

@Role(name = ManufacturerRole.NAME)
public class ManufacturerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Manufacturer";

    @ScreenAccess(screenIds = {"application-intership", "manufacturing-intership", "intership_ManufacturerOfGoods.browse", "intership_Product.browse", "intership_ManufacturerScreen", "intership_Shop.browse", "retail-intership", "intership_CommercialNetwork.browse", "intership_Product.edit", "intership_ProductInStore.browse", "intership_Shop.edit", "intership_CommercialNetwork.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = ProductInStore.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Shop.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Product.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = ManufacturerOfGoods.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = CommercialNetwork.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Address.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = ProductInStore.class, view = {"quantity", "price", "shop", "product"})
    @EntityAttributeAccess(entityClass = Shop.class, view = {"number", "productsInStore", "address", "shopType", "name", "commercialNetwork"})
    @EntityAttributeAccess(entityClass = Product.class, modify = "*")
    @EntityAttributeAccess(entityClass = ManufacturerOfGoods.class, view = {"name", "address", "fullName", "user"})
    @EntityAttributeAccess(entityClass = CommercialNetwork.class, view = {"name", "fullName"})
    @EntityAttributeAccess(entityClass = Address.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
