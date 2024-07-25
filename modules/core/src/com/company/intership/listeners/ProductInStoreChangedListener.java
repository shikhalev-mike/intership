package com.company.intership.listeners;

import com.company.intership.entity.PriceHistory;
import com.company.intership.entity.ProductInStore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("intership_ProductInStoreChangedListener")
public class ProductInStoreChangedListener {
    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<ProductInStore, UUID> event) {
        if (event.getType() == EntityChangedEvent.Type.CREATED || event.getType() == EntityChangedEvent.Type.UPDATED) {
            ProductInStore productInStore = dataManager.load(event.getEntityId())
                    .view("productInStore-view")
                    .one();
            PriceHistory priceHistory = dataManager.create(PriceHistory.class);
            priceHistory.setProduct(productInStore.getProduct());
            priceHistory.setShop(productInStore.getShop());
            priceHistory.setPriceChangeDate(LocalDate.now());
            AttributeChanges changes = event.getChanges();
            if (event.getType() == EntityChangedEvent.Type.UPDATED && changes.isChanged("price")) {
                BigDecimal oldPrice = changes.getOldValue("price");
                priceHistory.setOldPrice(oldPrice);
            } else if (event.getType() == EntityChangedEvent.Type.CREATED) {
                priceHistory.setOldPrice(BigDecimal.ZERO);
            }
            priceHistory.setNewPrice(productInStore.getPrice());
            dataManager.commit(priceHistory);
        }
    }
}