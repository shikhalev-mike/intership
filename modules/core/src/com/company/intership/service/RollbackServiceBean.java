package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.company.intership.entity.Status;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(RollbackService.NAME)
public class RollbackServiceBean implements RollbackService {

    private static final Logger log = LoggerFactory.getLogger(RollbackServiceBean.class);

    private final DataManager dataManager;

    @Inject
    public RollbackServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void rollback(OnlineOrder onlineOrder) {
        if (onlineOrder != null) {
            OnlineOrder order = dataManager.load(OnlineOrder.class)
                    .id(onlineOrder.getId())
                    .view("onlineOrder-view")
                    .one();
            log.info("Rolling back order: {}", order.getNumber());
            onlineOrder.setStatus(Status.CANCELLED);
            log.info("Set status {} to online-order: {}", onlineOrder.getStatus(), order.getNumber());
            List<ProductInPurchase> list = order.getProductsInPurchase();
            for (ProductInPurchase p : list) {
                ProductInStore productInStore = p.getProductInStore();
                log.info("Old quantity {}: {}", productInStore.getProduct().getName(), productInStore.getQuantity());
                productInStore.setQuantity(productInStore.getQuantity() + p.getQuantity());
                log.info("New quantity {}: {}", productInStore.getProduct().getName(), productInStore.getQuantity());
                dataManager.commit(productInStore);
            }
        }
    }
}