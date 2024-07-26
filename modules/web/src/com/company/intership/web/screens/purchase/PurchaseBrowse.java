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
}