package com.gurukula.testlib.widgets;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.webelements.Link;
import com.github.seleniumpm.pagemodel.webelements.Widget;
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
        super.waitForPresentAndVisible(sel.getPageTimeout());
        settingsLink.waitForPresentAndVisible(sel.getPageTimeout());
        passwordLink.waitForPresentAndVisible(sel.getPageTimeout());
        sessionsLink.waitForPresentAndVisible(sel.getPageTimeout());
        logoutLink.waitForPresentAndVisible(sel.getPageTimeout());
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
