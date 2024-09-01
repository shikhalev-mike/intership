package com.company.intership.service;

import com.company.intership.entity.Employee;
import com.company.intership.entity.ProductInStore;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(EmployeeService.NAME)
public class EmployeeServiceBean implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceBean.class);
    private final DataManager dataManager;

    @Inject
    public EmployeeServiceBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public User getRandUserAnEmployee() {
        User user = getRandEmployee().getUser();
        log.info("randUserAnEmployee: {}", user.getLogin());
        return user;
    }

    @Override
    public Employee getRandEmployee() {
        return dataManager
                .load(Employee.class)
                .query("select e from intership_Employee e order by function('RANDOM')")
                .view("employee-view")
                .one();
    }
}