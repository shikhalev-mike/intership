package com.company.intership.web.screens.shop;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Shop;

@UiController("intership_Shop.edit")
@UiDescriptor("shop-edit.xml")
@EditedEntityContainer("shopDc")
@LoadDataBeforeShow
public class ShopEdit extends StandardEditor<Shop> {
}