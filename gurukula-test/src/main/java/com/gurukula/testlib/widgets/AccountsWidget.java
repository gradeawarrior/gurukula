package com.gurukula.testlib.widgets;

import com.github.seleniumpm.webelements.Link;
import com.github.seleniumpm.webelements.Widget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsWidget extends Widget {
    // Unauthenticated
    public Link authenticateLink;
    public Link registerLink;

    // Authenticated
    public Link settingsLink;
    public Link passwordLink;
    public Link sessionsLink;
    public Link logoutLink;

    public AccountsWidget(WebDriver driver, By locator) {
        super(driver, locator);
        authenticateLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.account.login']"));
        registerLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.account.register']"));
        settingsLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.account.settings']"));
        passwordLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.account.password']"));
        sessionsLink = new Link(driver, By.xpath("//a/span[@translate='global.menu.account.sessions']"));
        logoutLink = new Link(driver, By.xpath("//a[@ng-click='logout()']"));
    }

    public AccountsWidget waitForPresentAndVisibleSelectedAuthenticated() {
        super.waitForPresentAndVisible(pageWaitTime);
        settingsLink.waitForPresentAndVisible(pageWaitTime);
        passwordLink.waitForPresentAndVisible(pageWaitTime);
        sessionsLink.waitForPresentAndVisible(pageWaitTime);
        logoutLink.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public AccountsWidget waitForPresentAndVisibleSelectedUnAuthenticated() {
        super.waitForPresentAndVisible();
        authenticateLink.waitForPresentAndVisible();
        registerLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        this.presentAndVisible();
    }
}
