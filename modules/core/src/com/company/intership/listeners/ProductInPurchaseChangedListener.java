package com.company.intership.listeners;

import com.company.intership.entity.ProductInPurchase;

import java.util.UUID;

import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("intership_ProductInPurchaseChangedListener")
public class ProductInPurchaseChangedListener {
    @Inject
    private TransactionalDataManager transactionalDataManager;

    @TransactionalEventListener
    public void beforeCommit(EntityChangedEvent<ProductInPurchase, UUID> event) {
        if (!event.getType().equals(EntityChangedEvent.Type.DELETED)) {
            ProductInPurchase editedEntity = transactionalDataManager.load(event.getEntityId())
                    .view("productInPurchase-view")
                    .one();
            ProductInStore productInStore = editedEntity.getProductInStore();

            transactionalDataManager.save(productInStore);
        }
    }

}