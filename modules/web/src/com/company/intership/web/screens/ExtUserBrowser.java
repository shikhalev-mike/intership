package com.company.intership.web.screens;

import com.company.intership.entity.ExtUser;
import com.company.intership.entity.JuridicalPerson;
import com.company.intership.entity.NaturalPerson;
import com.company.intership.service.BuyerWithUserService;
import com.haulmont.cuba.gui.app.security.user.browse.UserBrowser;
import com.haulmont.cuba.gui.screen.MessageBundle;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.Collection;

public class ExtUserBrowser extends UserBrowser {
    @Inject
    private BuyerWithUserService buyerWithUserService;
    @Inject
    private MessageBundle messageBundle;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        usersDs.refresh();
        Collection<User> list = usersDs.getItems();
        list.forEach(user -> {
            if (buyerWithUserService.isBuyerWithUserService(user)) {
                ((ExtUser) user).setBuyerInfo(typeOfUser((ExtUser) user) + " " + buyerWithUserService
                        .getBuyerByUserId(user)
                        .getFullName());
                usersDs.setItem(user);
            }
        });
    }

    private String typeOfUser(ExtUser user) {
        String s;
        if (buyerWithUserService.getBuyerByUserId(user).getClass().equals(NaturalPerson.class)) {
            s = messages.getMessage(getClass(), "natPersMessage");
        } else {
            s = messages.getMessage(getClass(), "jurPersMessage");
        }
        return s;
    }
}