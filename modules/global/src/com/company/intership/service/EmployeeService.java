package com.company.intership.service;

import com.company.intership.entity.Employee;
import com.haulmont.cuba.security.entity.User;

public interface EmployeeService {
    String NAME = "intership_EmployeeService";

    User getRandUserAnEmployee();

    Employee getRandEmployee();
}