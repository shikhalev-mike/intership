package com.company.intership.listeners;

import com.company.intership.entity.OnlineOrder;

import java.util.UUID;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;

@Component("intership_OnlineOrderChangedListener")
public class OnlineOrderChangedListener {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OnlineOrderChangedListener.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;
    @Inject
    private PlatformTransactionManager transactionManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<OnlineOrder, UUID> event) {
        try {
            if (event.getType().equals(EntityChangedEvent.Type.CREATED)) {
                TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                transactionTemplate.execute(status -> {
                    OnlineOrder onlineOrder = dataManager.load(event.getEntityId())
                            .view("onlineOrder-view")
                            .one();
                    String nextNumber = String.valueOf(uniqueNumbersAPI.getNextNumber("orderNumberSequence"));
                    onlineOrder.setNumber(nextNumber);
                    dataManager.commit(onlineOrder);
                    return null;
                });
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
    }
}