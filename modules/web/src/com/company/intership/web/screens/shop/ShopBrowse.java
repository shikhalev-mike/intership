package com.company.intership.web.screens.shop;

import com.company.intership.entity.ProductInStore;
import com.company.intership.web.screens.productinstore.ProductInStoreEdit;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Shop;

import javax.inject.Inject;

@UiController("intership_Shop.browse")
@UiDescriptor("shop-browse.xml")
@LookupComponent("shopsTable")
@LoadDataBeforeShow
public class ShopBrowse extends StandardLookup<Shop> {

}