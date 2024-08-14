package com.company.intership.web.screens.productinpurchase;

import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Purchase;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInPurchase;

import java.math.BigDecimal;

@UiController("intership_ProductInPurchase.edit")
@UiDescriptor("product-in-purchase-edit.xml")
@EditedEntityContainer("productInPurchaseDc")
@LoadDataBeforeShow
public class ProductInPurchaseEdit extends StandardEditor<ProductInPurchase> {
    private Purchase purchase;

    @Subscribe("productInStoreField")
    public void onProductInStoreFieldValueChange(HasValue.ValueChangeEvent<ProductInStore> event) {
        if (event.getValue() != null) {
            setPrice(event.getValue().getPrice());
        }
    }

    private void setPrice(BigDecimal price) {
        getEditedEntity().setPrice(price);
    }

    public void setPurchase(Purchase purchase) {
        getEditedEntity().setPurchase(purchase);
        this.purchase = purchase;
    }

    @Install(to = "productInStoreField.lookup", subject = "screenOptionsSupplier")
    private ScreenOptions productInStoreFieldLookupScreenOptionsSupplier() {
        return new MapScreenOptions(ParamsMap.of("shopId", purchase.getShop().getId()));
    }
}