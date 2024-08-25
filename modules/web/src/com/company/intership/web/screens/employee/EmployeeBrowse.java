package com.company.intership.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Employee;

@UiController("intership_Employee.browse")
@UiDescriptor("employee-browse.xml")
@LookupComponent("employeesTable")
@LoadDataBeforeShow
public class EmployeeBrowse extends StandardLookup<Employee> {
}