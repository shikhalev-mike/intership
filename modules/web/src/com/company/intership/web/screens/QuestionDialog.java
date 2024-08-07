package com.company.intership.web.screens;

import com.company.intership.entity.Buyer;
import com.company.intership.entity.ExtUser;
import com.company.intership.entity.JuridicalPerson;
import com.company.intership.entity.NaturalPerson;
import com.company.intership.web.screens.juridicalperson.JuridicalPersonEdit;
import com.company.intership.web.screens.naturalperson.NaturalPersonEdit;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.List;

@UiController("intership_QuestionDialog")
@UiDescriptor("question-dialog.xml")
public class QuestionDialog extends Screen {
    @Inject
    private ScreenBuilders screenBuilders;
    private User user;

    @Subscribe("cancelButton")
    public void onCancelButtonClick(Button.ClickEvent event) {
        closeWithDefaultAction();
    }

    @Subscribe("popupButton.popupAction1")
    public void onPopupButtonPopupAction1(Action.ActionPerformedEvent event) {
        NaturalPersonEdit screen = screenBuilders.editor(NaturalPerson.class, this)
                .newEntity()
                .withInitializer(naturalPerson -> {
                    naturalPerson.setUser((ExtUser) user);
                    naturalPerson.setFullName(user.getFirstName() + " "
                            + user.getMiddleName() + " " + user.getLastName());
                    naturalPerson.setName(user.getFirstName());
                    naturalPerson.setSurname(user.getMiddleName());
                    naturalPerson.setPatronymic(user.getLastName());
                    naturalPerson.setEmail(user.getEmail());
                })
                .withScreenClass(NaturalPersonEdit.class)
                .build();
        screen.show();
        closeWithDefaultAction();
    }

    @Subscribe("popupButton.popupAction2")
    public void onPopupButtonPopupAction2(Action.ActionPerformedEvent event) {
        JuridicalPersonEdit screen = screenBuilders.editor(JuridicalPerson.class, this)
                .newEntity()
                .withInitializer(juridicalPerson -> {
                    juridicalPerson.setUser((ExtUser) user);
                    juridicalPerson.setFullName(user.getFirstName() + " "
                            + user.getMiddleName() + " " + user.getLastName());
                    juridicalPerson.setName(user.getName());
                    juridicalPerson.setEmail(user.getEmail());
                })
                .withScreenClass(JuridicalPersonEdit.class)
                .build();
        screen.show();
        closeWithDefaultAction();
    }

    public void setUser(User user) {
        this.user = user;
    }
}