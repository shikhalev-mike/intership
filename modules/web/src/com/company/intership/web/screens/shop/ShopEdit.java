package com.company.intership.web.screens.shop;

import com.company.intership.entity.ProductInStore;
import com.company.intership.web.screens.productinstore.ProductInStoreEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Shop;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_Shop.edit")
@UiDescriptor("shop-edit.xml")
@EditedEntityContainer("shopDc")
@LoadDataBeforeShow
public class ShopEdit extends StandardEditor<Shop> {

    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private CollectionContainer<ProductInStore> productInStoresDc;
    @Inject
    private DataContext dataContext;

    @Install(to = "productInStoresTable.create", subject = "initializer")
    private void ProductInStoreTableCreateInitializer(ProductInStore productInStore) {
        productInStore.setShop(getEditedEntity());
    }

    @Subscribe("productInStoresTable.create")
    public void onProductInStoresTableCreate(Action.ActionPerformedEvent event) {
        ProductInStoreEdit screen = screenBuilders.editor(ProductInStore.class, this)
                .newEntity()
                .withInitializer(productInStores -> {
                    productInStores.setShop(getEditedEntity());
                })
                .withScreenClass(ProductInStoreEdit.class)
                .withParentDataContext(dataContext)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                        List<ProductInStore> list = productInStoresDc.getMutableItems();
                        ProductInStore productInStore = afterCloseEvent.getScreen().getEditedEntity();
                        boolean isDuplicate = false;
                        for (ProductInStore p : list) {
                            if (p.getProduct().equals(productInStore.getProduct())) {
                                p.setQuantity(p.getQuantity() + productInStore.getQuantity());
                                isDuplicate = true;
                            }
                        }
                        if (isDuplicate) {
                            dataContext.remove(productInStore);
                        } else {
                            list.add(productInStore);
                        }
                    }
                }).build();
        screen.show();
    }
}