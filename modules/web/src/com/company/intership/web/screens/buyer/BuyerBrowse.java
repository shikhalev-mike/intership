package com.company.intership.web.screens.buyer;

import com.company.intership.entity.JuridicalPerson;
import com.company.intership.entity.NaturalPerson;
import com.company.intership.web.screens.juridicalperson.JuridicalPersonEdit;
import com.company.intership.web.screens.naturalperson.NaturalPersonEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.intership.entity.Buyer;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_Buyer.browse")
@UiDescriptor("buyer-browse.xml")
@LookupComponent("buyersTable")
@LoadDataBeforeShow
public class BuyerBrowse extends StandardLookup<Buyer> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private CollectionContainer<Buyer> buyersDc;

    @Subscribe("popupButton.popupAction1")
    public void onPopupButtonPopupAction1(Action.ActionPerformedEvent event) {
        NaturalPersonEdit screen = screenBuilders.editor(NaturalPerson.class, this)
                .newEntity()
                .withScreenClass(NaturalPersonEdit.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    List<Buyer> list = buyersDc.getMutableItems();
                    list.add(afterCloseEvent.getScreen().getEditedEntity());
                })
                .build();
        screen.show();
    }

    @Subscribe("popupButton.popupAction2")
    public void onPopupButtonPopupAction2(Action.ActionPerformedEvent event) {
        JuridicalPersonEdit screen = screenBuilders.editor(JuridicalPerson.class, this)
                .newEntity()
                .withScreenClass(JuridicalPersonEdit.class)
                .withAfterCloseListener(afterCloseEvent -> {
                    List<Buyer> list = buyersDc.getMutableItems();
                    list.add(afterCloseEvent.getScreen().getEditedEntity());
                })
                .build();
        screen.show();
    }
}