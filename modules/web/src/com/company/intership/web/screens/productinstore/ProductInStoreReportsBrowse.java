package com.company.intership.web.screens.productinstore;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductInStore;

@UiController("intership_ProductInStoreReports.browse")
@UiDescriptor("product-in-store-reports-browse.xml")
@LookupComponent("productInStoresTable")
@LoadDataBeforeShow
public class ProductInStoreReportsBrowse extends StandardLookup<ProductInStore> {
}