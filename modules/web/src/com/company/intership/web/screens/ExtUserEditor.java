package com.company.intership.web.screens;

import com.company.intership.service.BuyerWithUserService;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.app.security.user.edit.UserEditor;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;

import javax.inject.Inject;

public class ExtUserEditor extends UserEditor {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private BuyerWithUserService buyerWithUserService;

    @Override
    protected void beforeClose(Screen.BeforeCloseEvent event) {
        super.beforeClose(event);
        if (!buyerWithUserService.isBuyerWithUserService(getEditedEntity())) {
            QuestionDialog dialog = screenBuilders.screen(this)
                    .withScreenClass(QuestionDialog.class)
                    .withLaunchMode(OpenMode.DIALOG)
                    .build();
            dialog.setUser(getEditedEntity());
            dialog.show();
        }
    }
}