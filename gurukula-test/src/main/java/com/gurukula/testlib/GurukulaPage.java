package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;
import com.gurukula.testlib.widgets.AccountsWidget;
import com.gurukula.testlib.widgets.HeaderWidget;
import com.gurukula.testlib.widgets.LoginWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class GurukulaPage extends WebPage {
    public LoginWidget loginWidget;
    public HeaderWidget headerWidget;

    public GurukulaPage(Selenium sel) {
        super(sel);
        loginWidget = new LoginWidget(sel, By.xpath("//div[contains(@class, 'col-md-8')]/div[@ng-switch='isAuthenticated()']"));
        headerWidget = new HeaderWidget(sel, By.id("navbar-collapse"));
    }

    public GurukulaPage(Selenium sel, String path) {
        super(sel, path);
        loginWidget = new LoginWidget(sel, By.xpath("//div[contains(@class, 'col-md-8')]/div[@ng-switch='isAuthenticated()']"));
        headerWidget = new HeaderWidget(sel, By.id("navbar-collapse"));
    }

    public boolean isLoggedIn() {
        // TODO - Disabling to speed-up tests. Right now, elements not present and visible are taking too long!
        // return (!loginWidget.loginLink.isPresentAndVisible() && !loginWidget.registerAccountLink.isPresentAndVisible()) || headerWidget.accountsWidget.logoutLink.isPresent();
        return headerWidget.accountsWidget.logoutLink.isPresent();
    }

    public WebPage waitForPageLoad() {
        // TODO - Adding a little sleep to help things along when waiting for elements to show up on the page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!(sel.getDriver() instanceof HtmlUnitDriver))
            headerWidget.waitForPresentAndVisible(pageWaitTime);
        return this;
    }

    public void validate() {
        headerWidget.validate();
    }
}
