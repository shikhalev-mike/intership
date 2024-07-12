package com.company.intership.web.screens.commercialnetwork;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.CommercialNetwork;

@UiController("intership_CommercialNetwork.browse")
@UiDescriptor("commercial-network-browse.xml")
@LookupComponent("commercialNetworksTable")
@LoadDataBeforeShow
public class CommercialNetworkBrowse extends StandardLookup<CommercialNetwork> {
}