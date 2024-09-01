package com.company.intership.web.screens;

import com.company.intership.entity.*;
import com.company.intership.service.BuyerWithUserService;
import com.company.intership.service.EmployeeService;
import com.google.common.collect.ImmutableMap;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.Target;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.company.intership.web.screens.purchase.PurchaseEdit;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@UiController("intership_OnlineOrder.edit")
@UiDescriptor("online-order-edit.xml")
public class OnlineOrderEdit extends PurchaseEdit {
    private static final Logger log = LoggerFactory.getLogger(OnlineOrderEdit.class);
    @Inject
    private TextField<String> discountField;
    @Inject
    private TextField<String> totalAmountField;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private TextField<String> buyerField;
    @Inject
    private BuyerWithUserService buyerWithUserService;
    protected boolean isNew;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private BprocRuntimeService bprocRuntimeService;
    private static final String PROCESS_CODE = "online-order-process";

    @Subscribe
    public void onInitEntity(InitEntityEvent<Purchase> event) {
        if (event.getEntity() instanceof OnlineOrder) {
            isNew = true;
            ((OnlineOrder) event.getEntity()).setStatus(Status.NEW);
        }
    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        if (isNew) {
            log.info("onAfterCommitChanges...");
            OnlineOrder onlineOrder = (OnlineOrder) getEditedEntity();

            Map<String, Object> processVariables = ImmutableMap.of(
                    "onlineOrder", onlineOrder,
                    "buyer", userSessionSource.getUserSession().getUser(),
                    "employee", employeeService.getRandUserAnEmployee()
            );

            String businessKey = onlineOrder.getNumber();

            bprocRuntimeService.startProcessInstanceByKey(PROCESS_CODE,
                    businessKey,
                    processVariables);
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User user = userSessionSource.getUserSession().getUser();
        Buyer buyer = buyerWithUserService.getBuyerByUserId(user);
        if (buyer.getFullName() != null) {
            buyerField.setValue(buyer.getFullName());
        } else {
            buyerField.setValue("");
        }
        ((OnlineOrder) getEditedEntity()).setBuyer(buyer);
        ((OnlineOrder) getEditedEntity()).setDiscount(0);
    }

    @Subscribe(id = "productInPurchasesDc", target = Target.DATA_CONTAINER)
    public void onProductInPurchasesDcCollectionChange(CollectionContainer.CollectionChangeEvent<ProductInPurchase> event) {
        Collection<ProductInPurchase> collection = event.getSource().getMutableItems();
        BigDecimal amount = BigDecimal.valueOf(0);
        for (ProductInPurchase productInPurchase : collection) {
            BigDecimal price = productInPurchase.getPrice();
            amount = amount.add(price);
        }
        ((OnlineOrder) getEditedEntity()).setOrderAmount(amount);
        totalAmountField.setValue(amount.toString());
    }

    @Subscribe("discointButton")
    public void onDiscointButtonClick(Button.ClickEvent event) {
        int discount = -1;
        if (discountField.getValue() != null) {
            try {
                discount = Integer.parseInt(Objects.requireNonNull(discountField.getValue()));
                ((OnlineOrder) getEditedEntity()).setDiscount(discount);
            } catch (NumberFormatException e) {
                discountField.setValue("0");
                ((OnlineOrder) getEditedEntity()).setDiscount(discount);
            }
            if (discount > 0 && discount < 100) {
                ((OnlineOrder) getEditedEntity()).setDiscount(discount);
                BigDecimal disc = BigDecimal.valueOf(discount);
                disc = disc.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                BigDecimal amount = BigDecimal.
                        valueOf(Double
                                .parseDouble(String
                                        .valueOf(Objects
                                                .requireNonNull(((OnlineOrder) getEditedEntity()).getOrderAmount()))));


                amount = amount.subtract(amount.multiply(disc));
                ((OnlineOrder) getEditedEntity()).setOrderAmount(amount);
                totalAmountField.setValue(amount.toString());
            }
        } else {
            discountField.setValue("0");
        }
    }
}