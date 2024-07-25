package com.company.intership.web.screens.purchase;

import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Purchase;

import javax.inject.Inject;

@UiController("intership_Purchase.browse")
@UiDescriptor("purchase-browse.xml")
@LookupComponent("purchasesTable")
@LoadDataBeforeShow
public class PurchaseBrowse extends StandardLookup<Purchase> {
    @Inject
    private GroupTable<ProductInPurchase> productInPurchasesTable;

    @Subscribe
    public void onInit(InitEvent event) {
        productInPurchasesTable.setVisible(false);
    }

    @Subscribe("purchasesTable")
    public void onPurchasesTableSelection(Table.SelectionEvent<Purchase> event) {
        if (event.getSelected().size() == 1) {
            Purchase purchase = event.getSelected().iterator().next();
            productInPurchasesTable.setVisible(true);
        }
    }

}