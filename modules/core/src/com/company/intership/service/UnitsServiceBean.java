package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.ProductInPurchase;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(UnitsService.NAME)
public class UnitsServiceBean implements UnitsService {

    private final DataManager dataManager;

    private static final Logger log = LoggerFactory.getLogger(UnitsServiceBean.class);

    @Inject
    public UnitsServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUnitsInStock(OnlineOrder onlineOrder) {
        OnlineOrder order = dataManager.load(OnlineOrder.class)
                .id(onlineOrder.getId())
                .view("onlineOrder-view")
                .one();
        log.info("change units in stock: {}", order.getNumber());
        List<ProductInPurchase> list = order.getProductsInPurchase();
        for (ProductInPurchase p : list) {
            ProductInStore productInStore = p.getProductInStore();
            log.info("Old quantity {}: {}", productInStore.getProduct().getName(), productInStore.getQuantity());
            productInStore.setQuantity(productInStore.getQuantity() - p.getQuantity());
            log.info("New quantity {}: {}", productInStore.getProduct().getName(), productInStore.getQuantity());
            dataManager.commit(productInStore);
        }
    }
}