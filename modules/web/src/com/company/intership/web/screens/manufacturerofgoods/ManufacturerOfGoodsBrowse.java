package com.company.intership.web.screens.manufacturerofgoods;

import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.ManufacturerOfGoods;

@UiController("intership_ManufacturerOfGoods.browse")
@UiDescriptor("manufacturer-of-goods-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class ManufacturerOfGoodsBrowse extends MasterDetailScreen<ManufacturerOfGoods> {
}