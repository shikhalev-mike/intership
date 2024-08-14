package com.company.intership.web.screens;

import com.company.intership.entity.CommercialNetwork;
import com.company.intership.entity.Purchase;
import com.company.intership.entity.Shop;
import com.company.intership.service.ShopService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_ShopScreen")
@UiDescriptor("shop-screen.xml")
public class ShopScreen extends Screen {
    private static final Logger log = LoggerFactory.getLogger(ShopScreen.class);
    @Inject
    private CollectionContainer<Purchase> purchasesDc;
    @Inject
    private CollectionContainer<Purchase> purchasesDc1;
    @Inject
    private PickerField<CommercialNetwork> commercialNetworkPicker;
    @Inject
    private PickerField<Shop> shopPicker;
    @Inject
    private ShopService shopService;

    @Subscribe("updateBtn")
    public void onUpdateBtnClick(Button.ClickEvent event) {
        loadPurchasesByShop();
    }

    @Subscribe("updateBtn1")
    public void onUpdateBtn1Click(Button.ClickEvent event) {
        loadPurchasesByCommercialNetwork();
    }

    public void loadPurchasesByShop() {
        if (shopPicker.getValue() != null) {
            List<Purchase> purchases = shopService.getPurchasesByShop(
                    shopPicker.getValue()
            );
            purchasesDc.setItems(purchases);
        }
    }

    public void loadPurchasesByCommercialNetwork() {
        if (commercialNetworkPicker.getValue() != null) {
            List<Purchase> purchases = shopService.getPurchasesByCommercialNetwork(
                    commercialNetworkPicker.getValue()
            );
            purchasesDc1.setItems(purchases);
        }
    }
}