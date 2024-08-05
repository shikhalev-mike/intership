package com.company.intership.listeners;

import com.company.intership.entity.OnlineOrder;

import java.util.UUID;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("intership_OnlineOrderChangedListener")
public class OnlineOrderChangedListener {
    @Inject
    private TransactionalDataManager txDm;
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<OnlineOrder, UUID> event) {
        if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
            OnlineOrder onlineOrder = txDm.load(event.getEntityId())
                    .view("onlineOrder-view")
                    .one();
            onlineOrder.setNumber(String.valueOf(uniqueNumbersAPI.getNextNumber("orderNumberSequence")));
            txDm.save(onlineOrder);
        }
    }
}