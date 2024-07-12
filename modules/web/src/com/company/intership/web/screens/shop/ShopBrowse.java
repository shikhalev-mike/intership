package com.company.intership.web.screens.shop;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Shop;

@UiController("intership_Shop.browse")
@UiDescriptor("shop-browse.xml")
@LookupComponent("shopsTable")
@LoadDataBeforeShow
public class ShopBrowse extends StandardLookup<Shop> {
}