package com.company.intership.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Product;

@UiController("intership_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}