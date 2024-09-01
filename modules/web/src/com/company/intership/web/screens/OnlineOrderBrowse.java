package com.company.intership.web.screens;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Purchase;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.company.intership.web.screens.purchase.PurchaseBrowse;

import javax.inject.Inject;

@UiController("OnlineOrder.browse")
@UiDescriptor("online-order-browse.xml")
public class OnlineOrderBrowse extends PurchaseBrowse {
    @Inject
    private GroupTable<Purchase> purchasesTable;
    @Inject
    private VBoxLayout productInPurchaseBox;
    @Inject
    private VBoxLayout onlineOrderInfo;

    @Subscribe
    public void onInit(InitEvent event) {
        purchasesTable.addSelectionListener(selectionEvent -> {
            Purchase selected = purchasesTable.getSingleSelected();
            onlineOrderInfo.setVisible(selected instanceof OnlineOrder);
        });
    }
    
}