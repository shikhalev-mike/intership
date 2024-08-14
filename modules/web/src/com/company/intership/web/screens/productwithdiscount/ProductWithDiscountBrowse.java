package com.company.intership.web.screens.productwithdiscount;

import com.company.intership.entity.ProductInStore;
import com.company.intership.service.ProductService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ProductWithDiscount;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@UiController("intership_ProductWithDiscount.browse")
@UiDescriptor("product-with-discount-browse.xml")
@LookupComponent("productWithDiscountsTable")
@LoadDataBeforeShow
public class ProductWithDiscountBrowse extends StandardLookup<ProductWithDiscount> {
    @Inject
    protected DataManager dataManager;
    @Inject
    private CollectionContainer<ProductWithDiscount> productWithDiscountsDc;
    @Inject
    private CollectionContainer<ProductWithDiscount> productWithDiscountsDc1;
    @Inject
    private ProductService productService;
    private ProductWithDiscount product;
    private boolean isChoose;
    private ProductWithDiscount productInCart;
    private boolean isChooseInCart;
    @Inject
    private DataContext dataContext;

    @Subscribe("productInCartTable")
    public void onProductInCartTableSelection(Table.SelectionEvent<ProductWithDiscount> event) {
        if (event.getSelected().size() == 1) {
            isChooseInCart = true;
            productInCart = event.getSelected().iterator().next();
        } else isChooseInCart = false;
    }

    @Subscribe("delBtn")
    public void onDelBtnClick(Button.ClickEvent event) {
        if (isChooseInCart) {
            productWithDiscountsDc1.getMutableItems().remove(productInCart);
        }
    }

    @Subscribe("productWithDiscountsTable")
    public void onProductWithDiscountsTableSelection(Table.SelectionEvent<ProductWithDiscount> event) {
        if (event.getSelected().size() == 1) {
            isChoose = true;
            product = event.getSelected().iterator().next();
        } else isChoose = false;
    }

    @Subscribe("addBtn")
    public void onAddBtnClick(Button.ClickEvent event) {
        boolean isDuplicate = false;
        if (isChoose) {
            for (ProductWithDiscount productWithDiscount : productWithDiscountsDc1.getMutableItems()) {
                if (product.equals(productWithDiscount)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                productWithDiscountsDc1.getMutableItems().add(product);
            }
        }
    }

    @Subscribe("generateBtn")
    public void onGenerateBtnClick(Button.ClickEvent event) {
        productWithDiscountsDc.getMutableItems().clear();
        int discount = LocalDate.now().getDayOfMonth();
        List<ProductInStore> list = productService.getRandomProducts(10);
        List<ProductWithDiscount> productWithDiscounts = productWithDiscountsDc.getMutableItems();
        for (ProductInStore listItem : list) {
            ProductWithDiscount productWithDiscount = dataManager.create(ProductWithDiscount.class);
            productWithDiscount.setProduct(listItem.getProduct());
            productWithDiscount.setShop(listItem.getShop());
            productWithDiscount.setPrice(listItem.getPrice());
            BigDecimal disc = BigDecimal.valueOf(discount);
            disc = disc.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal amount = BigDecimal.
                    valueOf(Double
                            .parseDouble(String
                                    .valueOf(Objects
                                            .requireNonNull(listItem.getPrice()))));

            amount = amount.subtract(amount.multiply(disc));
            productWithDiscount.setPriceWithDiscount(amount);
            productWithDiscounts.add(productWithDiscount);
        }
    }
}