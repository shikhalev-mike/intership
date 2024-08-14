package com.company.intership.web.screens.purchase;

import com.company.intership.entity.*;
import com.company.intership.web.screens.productinpurchase.ProductInPurchaseEdit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.actions.list.CreateAction;
import com.haulmont.cuba.gui.actions.list.EditAction;
import com.haulmont.cuba.gui.actions.list.RemoveAction;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

@UiController("intership_Purchase.edit")
@UiDescriptor("purchase-edit.xml")
@EditedEntityContainer("purchaseDc")
@LoadDataBeforeShow
public class PurchaseEdit extends StandardEditor<Purchase> {
    private static final Logger log = LoggerFactory.getLogger(PurchaseEdit.class);
    @Inject
    private PickerField<Shop> shopField;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataContext dataContext;
    @Inject
    private Notifications notifications;
    @Named("productInPurchasesTable.remove")
    private RemoveAction<ProductInPurchase> productInPurchasesTableRemove;
    @Inject
    private MessageBundle messageBundle;
    @Named("productInPurchasesTable.edit")
    private EditAction<ProductInPurchase> productInPurchasesTableEdit;
    @Inject
    private CollectionContainer<ProductInPurchase> productInPurchasesDc;
    @Named("productInPurchasesTable.create")
    private CreateAction<ProductInPurchase> productInPurchasesTableCreate;
    @Inject
    private CollectionLoader<ProductInPurchase> productInPurchasesDl;
    @Inject
    private DataManager dataManager;

    @Subscribe(id = "productInPurchasesDc", target = Target.DATA_CONTAINER)
    public void onProductInPurchasesDcItemChange(InstanceContainer.ItemChangeEvent<ProductInPurchase> event) {
        log.info(String.valueOf(Objects.requireNonNull(event.getItem()).getPurchase()));
    }

    @Subscribe
    public void onInit(InitEvent event) {
        productInPurchasesTableCreate.setEnabled(false);
        productInPurchasesTableEdit.setEnabled(false);
        productInPurchasesTableRemove.setEnabled(false);
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        if (!(getEditedEntity() instanceof OnlineOrder)) {
            List<ProductInPurchase> list = productInPurchasesDc.getMutableItems();
            for (ProductInPurchase p : list) {
                ProductInStore productInStore = p.getProductInStore();
                productInStore.setQuantity(productInStore.getQuantity() - p.getQuantity());
            }
        }
    }

    @Subscribe("shopField")
    public void onShopFieldValueChange(HasValue.ValueChangeEvent<Shop> event) {
        if (event.getValue() != null) {
            shopField.setEnabled(false);
            productInPurchasesTableCreate.setEnabled(true);
            productInPurchasesTableEdit.setEnabled(true);
            productInPurchasesTableRemove.setEnabled(true);
        }
    }

    @Subscribe("productInPurchasesTable.create")
    public void onProductInPurchasesTableCreate(Action.ActionPerformedEvent event) {
        ProductInPurchaseEdit screen = screenBuilders.editor(ProductInPurchase.class, this)
                .newEntity()
                .withScreenClass(ProductInPurchaseEdit.class)
                .withParentDataContext(dataContext)
                .withAfterCloseListener(afterCloseEvent -> {
                    if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                        ProductInPurchase editedEntity = afterCloseEvent.getScreen().getEditedEntity();
                        ProductInStore productInStore = editedEntity.getProductInStore();

                        if (editedEntity.getQuantity() > productInStore.getQuantity()) {
                            notifications.create()
                                    .withCaption(productInStore.getProduct().getName()
                                            + " - не хватает товара")
                                    .withType(Notifications.NotificationType.HUMANIZED)
                                    .show();
                        } else {
                            if (getDuplicate(editedEntity) != null) {
                                ProductInPurchase p = getDuplicate(editedEntity);
                                assert p != null;
                                if ((p.getQuantity() + editedEntity.getQuantity()) > productInStore.getQuantity()) {
                                    notifications.create()
                                            .withCaption(productInStore.getProduct().getName()
                                                    + " - не хватает товара")
                                            .withType(Notifications.NotificationType.HUMANIZED)
                                            .show();
                                } else {
                                    p.setQuantity(p.getQuantity() + editedEntity.getQuantity());
                                    dataContext.remove(editedEntity);
                                }
                            } else {
                                productInPurchasesDc.getMutableItems().add(dataContext.merge(editedEntity));
                            }
                        }
                    }
                })
                .build();
        screen.setPurchase(getEditedEntity());
        screen.show();
    }

    private ProductInPurchase getDuplicate(ProductInPurchase productInPurchase) {
        for (ProductInPurchase p : productInPurchasesDc.getMutableItems()) {
            if (p.getProductInStore().equals(productInPurchase.getProductInStore())) {
                return p;
            }
        }
        return null;
    }
}