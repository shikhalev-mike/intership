package com.company.intership.web.screens.productinpurchase;

import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Purchase;
import com.company.intership.web.screens.productinstore.ProductInStoreBrowse;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInPurchase;

import javax.inject.Inject;

@UiController("intership_ProductInPurchase.edit")
@UiDescriptor("product-in-purchase-edit.xml")
@EditedEntityContainer("productInPurchaseDc")
@LoadDataBeforeShow
public class ProductInPurchaseEdit extends StandardEditor<ProductInPurchase> {
    private Purchase purchase;

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Install(to = "productInStoreField.lookup", subject = "screenOptionsSupplier")
    private ScreenOptions productInStoreFieldLookupScreenOptionsSupplier() {
        return new MapScreenOptions(ParamsMap.of("shopId", purchase.getShop().getId()));
    }
}