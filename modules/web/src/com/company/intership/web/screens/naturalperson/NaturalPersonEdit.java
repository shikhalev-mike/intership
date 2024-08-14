package com.company.intership.web.screens.naturalperson;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.NaturalPerson;

@UiController("intership_NaturalPerson.edit")
@UiDescriptor("natural-person-edit.xml")
@EditedEntityContainer("naturalPersonDc")
@LoadDataBeforeShow
public class NaturalPersonEdit extends StandardEditor<NaturalPerson> {
}