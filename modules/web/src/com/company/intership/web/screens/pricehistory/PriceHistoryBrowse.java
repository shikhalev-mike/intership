package com.company.intership.web.screens.pricehistory;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.PriceHistory;

@UiController("intership_PriceHistory.browse")
@UiDescriptor("price-history-browse.xml")
@LookupComponent("priceHistoriesTable")
@LoadDataBeforeShow
public class PriceHistoryBrowse extends StandardLookup<PriceHistory> {
}