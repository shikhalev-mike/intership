package com.company.intership.service;

import com.company.intership.entity.OnlineOrder;
import com.company.intership.entity.Status;
import com.haulmont.cuba.core.global.DataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(StatusService.NAME)
public class StatusServiceBean implements StatusService {

    private static final Logger log = LoggerFactory.getLogger(StatusServiceBean.class);
    @Inject
    private DataManager dataManager;

    @Override
    public void changeStatus(OnlineOrder onlineOrder, String status) {
        OnlineOrder order = dataManager.load(OnlineOrder.class)
                .id(onlineOrder.getId())
                .view("onlineOrder-view")
                .one();
        order.setStatus(Status.valueOf(status));
        log.info("online order {} status changed to {}", order.getNumber(), order.getStatus());
        dataManager.commit(order);
    }
}