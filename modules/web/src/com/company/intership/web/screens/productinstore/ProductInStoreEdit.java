package com.company.intership.web.screens.productinstore;

import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInStore;

import javax.inject.Inject;

import java.util.List;

import static com.haulmont.cuba.gui.components.Window.COMMIT_ACTION_ID;
import static com.haulmont.cuba.gui.components.Window.EDITOR_WINDOW_SUFFIX;

@UiController("intership_ProductInStore.edit")
@UiDescriptor("product-in-store-edit.xml")
@EditedEntityContainer("productInStoreDc")
@LoadDataBeforeShow
@PrimaryEditorScreen(ProductInStore.class)
public class ProductInStoreEdit extends StandardEditor<ProductInStore> {
}