package com.company.intership.web.screens.purchase;

import com.company.intership.entity.*;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@UiController("intership_Purchase.browse")
@UiDescriptor("purchase-browse.xml")
@LookupComponent("purchasesTable")
@LoadDataBeforeShow
public class PurchaseBrowse extends StandardLookup<Purchase> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private GroupTable<Purchase> purchasesTable;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private GroupBoxLayout onlineOrderInfoBox;
    @Inject
    private TextField<String> totalAmountField;
    @Inject
    private TextField<String> orderNumberField;
    @Inject
    private TextField<String> discountField;
    @Inject
    private TextField<String> customerField;
    @Inject
    private DataContext dataContext;

    @Subscribe
    public void onInit(InitEvent event) {
        purchasesTable.addSelectionListener(selectionEvent -> {
            Purchase selected = purchasesTable.getSingleSelected();
            if (selected != null) {
                if (selected instanceof OnlineOrder) {
                    OnlineOrder onlineOrder = (OnlineOrder) selected;

                    OnlineOrder fullyLoadedOrder = dataManager.load(OnlineOrder.class)
                            .id(onlineOrder.getId())
                            .view("onlineOrder-view")
                            .one();
                    onlineOrderInfoBox.setVisible(true);
                    BigDecimal amount = BigDecimal.ZERO;
                    List<ProductInPurchase> list = fullyLoadedOrder.getProductsInPurchase();
                    for (ProductInPurchase productInPurchase : list) {
                        amount = amount.add(productInPurchase.getPrice());
                    }
                    totalAmountField.setValue(amount.toString());
                    orderNumberField.setValue(fullyLoadedOrder.getNumber());
                    discountField.setValue(fullyLoadedOrder.getDiscount().toString());
                    customerField.setValue(fullyLoadedOrder.getBuyer().getFullName());
                } else {
                    onlineOrderInfoBox.setVisible(false);
                }
            }
        });
    }

    @Subscribe("popupButton.popupAction1")
    public void onPopupButtonPopupAction1(Action.ActionPerformedEvent event) {
        Purchase purchase = dataManager.create(Purchase.class);
        showCreateEditor(purchase);
    }

    @Subscribe("popupButton.popupAction2")
    public void onPopupButtonPopupAction2(Action.ActionPerformedEvent event) {
        OnlineOrder onlineOrder = dataManager.create(OnlineOrder.class);
        showCreateEditor(onlineOrder);
    }

    private void showCreateEditor(Purchase purchase) {
        screenBuilders.editor(purchasesTable)
                .editEntity(purchase)
                .build()
                .show();
    }

}