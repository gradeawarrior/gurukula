package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Link;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class AccountsWidget extends Widget {
    // Unauthenticated
    public Link authenticateLink;
    public Link registerLink;

    // Authenticated
    public Link settingsLink;
    public Link passwordLink;
    public Link sessionsLink;
    public Link logoutLink;

    public AccountsWidget(Selenium sel, Object locator) {
        super(sel, locator);
        authenticateLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.account.login']"));
        registerLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.account.register']"));
        settingsLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.account.settings']"));
        passwordLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.account.password']"));
        sessionsLink = new Link(sel, By.xpath("//a/span[@translate='global.menu.account.sessions']"));
        logoutLink = new Link(sel, By.xpath("//a[@ng-click='logout()']"));
    }

    public AccountsWidget waitForPresentAndVisibleSelectedAuthenticated() {
        super.waitForPresentAndVisible();
        settingsLink.waitForPresentAndVisible();
        passwordLink.waitForPresentAndVisible();
        sessionsLink.waitForPresentAndVisible();
        logoutLink.waitForPresentAndVisible();
        return this;
    }

    public AccountsWidget waitForPresentAndVisibleSelectedUnAuthenticated() {
        super.waitForPresentAndVisible();
        authenticateLink.waitForPresentAndVisible();
        registerLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        super.validate();
    }
}
