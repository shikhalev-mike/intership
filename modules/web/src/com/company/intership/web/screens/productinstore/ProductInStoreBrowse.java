package com.company.intership.web.screens.productinstore;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;
import java.util.*;

@UiController("intership_ProductInStore.browse")
@UiDescriptor("product-in-store-browse.xml")
@LookupComponent("productInStoresTable")
@LoadDataBeforeShow
public class ProductInStoreBrowse extends StandardLookup<ProductInStore> {
    @Inject
    private CollectionLoader<ProductInStore> productInStoresDl;
    @Inject
    private HBoxLayout lookupActions;

    public ProductInStore getSelectedProductInStore() {
        return (ProductInStore) getLookupComponent();
    }

    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions mapScreenOptions = (MapScreenOptions) event.getOptions();
        UUID shopId = (UUID) mapScreenOptions.getParams().get("shopId");
        if (shopId != null) {
            productInStoresDl.setParameter("shopId", shopId);
            productInStoresDl.load();
        }
    }

    @Subscribe("productInStoresTable")
    public void onProductInStoresTableSelection(Table.SelectionEvent<ProductInStore> event) {
        if (event.getSelected().size() == 1) {
            ProductInStore productInStore = event.getSelected().iterator().next();
            lookupActions.setEnabled(productInStore.getQuantity() != 0 && productInStore.getQuantity() != null);
        }
    }
}