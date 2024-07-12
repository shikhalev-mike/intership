package com.company.intership.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Product;

@UiController("intership_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}