package com.company.intership.web.screens.juridicalperson;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.JuridicalPerson;

@UiController("intership_JuridicalPerson.edit")
@UiDescriptor("juridical-person-edit.xml")
@EditedEntityContainer("juridicalPersonDc")
@LoadDataBeforeShow
public class JuridicalPersonEdit extends StandardEditor<JuridicalPerson> {
}