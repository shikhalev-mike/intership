package com.company.intership.web.screens;

import com.company.intership.entity.*;
import com.company.intership.service.ManufacturerOfGoodsService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@UiController("intership_ManufacturerScreen")
@UiDescriptor("manufacturer-screen.xml")
public class ManufacturerScreen extends Screen {
    @Inject
    private PickerField<Shop> shopPicker;
    @Inject
    private TextField<String> maxQuantityField;
    @Inject
    private TextField<String> maxQuantityField1;
    @Inject
    private PickerField<ManufacturerOfGoods> manufacturerPicker1;
    @Inject
    private PickerField<ManufacturerOfGoods> manufacturerPicker;
    @Inject
    private CollectionContainer<ProductInStore> productInStoresDc;
    @Inject
    private CollectionContainer<ProductInStore> productInStoresDc1;
    @Inject
    private ManufacturerOfGoodsService manufacturerOfGoodsService;
    @Inject
    private PickerField<Product> productPicker;
    @Inject
    private CollectionContainer<Shop> shopsDc;

    @Subscribe("updateBtn")
    public void onUpdateBtnClick(Button.ClickEvent event) {
        loadProductsByManufacturerInStore();
    }

    @Subscribe("updateBtn1")
    public void onUpdateBtn1Click(Button.ClickEvent event) {
        loadProductsByManufacturerInAllStores();
    }

    @Subscribe("updateBtn2")
    public void onUpdateBtn2Click(Button.ClickEvent event) {
        loadStoresWithoutProduct();
    }

    public void loadProductsByManufacturerInStore() {
        if (manufacturerPicker.getValue() != null && shopPicker.getValue() != null
                && maxQuantityField.getValue() != null) {
            List<ProductInStore> products = manufacturerOfGoodsService.getProductsByManufacturerInStore(
                    manufacturerPicker.getValue(),
                    shopPicker.getValue(),
                    Integer.parseInt(Objects.requireNonNull(maxQuantityField.getValue()))
            );
            productInStoresDc.setItems(products);
        }
    }

    public void loadProductsByManufacturerInAllStores() {
        if (manufacturerPicker1.getValue() != null && maxQuantityField1.getValue() != null) {
            List<ProductInStore> products = manufacturerOfGoodsService.getProductsByManufacturerInAllStores(
                    manufacturerPicker1.getValue(),
                    Integer.parseInt(Objects.requireNonNull(maxQuantityField1.getValue()))
            );
            productInStoresDc1.setItems(products);
        }
    }

    public void loadStoresWithoutProduct() {
        if (productPicker.getValue() != null) {
            List<Shop> shops = manufacturerOfGoodsService.getStoresWithoutProduct(
                    productPicker.getValue()
            );
            shopsDc.setItems(shops);
        }
    }
}