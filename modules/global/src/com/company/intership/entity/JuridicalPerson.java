package com.company.intership.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "INTERSHIP_JURIDICAL_PERSON")
@Entity(name = "intership_JuridicalPerson")
@NamePattern("%s|name")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class JuridicalPerson extends Buyer {
    private static final long serialVersionUID = 5771819974754763572L;

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}