package com.company.intership.web.screens.employee;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Employee;

@UiController("intership_Employee.edit")
@UiDescriptor("employee-edit.xml")
@EditedEntityContainer("employeeDc")
@LoadDataBeforeShow
public class EmployeeEdit extends StandardEditor<Employee> {
}