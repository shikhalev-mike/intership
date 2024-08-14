package com.company.intership.web.screens.productinstore;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInStore;

@UiController("intership_ProductInStore.edit")
@UiDescriptor("product-in-store-edit.xml")
@EditedEntityContainer("productInStoreDc")
@LoadDataBeforeShow
@PrimaryEditorScreen(ProductInStore.class)
public class ProductInStoreEdit extends StandardEditor<ProductInStore> {
}