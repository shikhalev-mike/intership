package com.company.intership.entity;

import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;

@Entity(name = "intership_ExtUser")
public class ExtUser extends User {
    private static final long serialVersionUID = 5641185631021970542L;
}