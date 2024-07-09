package com.company.intership.web.screens.commercialnetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.CommercialNetwork;

@UiController("intership_CommercialNetwork.edit")
@UiDescriptor("commercial-network-edit.xml")
@EditedEntityContainer("commercialNetworkDc")
@LoadDataBeforeShow
public class CommercialNetworkEdit extends StandardEditor<CommercialNetwork> {
}