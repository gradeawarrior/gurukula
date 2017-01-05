package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HomePageUnauthenticated extends GurukulaPage {

    public HomePageUnauthenticated(Selenium sel) {
        super(sel);
        path = "/#/";
    }

    public HomePageUnauthenticated logout() {
        headerWidget.waitForPresentAndVisible();
        if (isLoggedIn()) {
            headerWidget.accountsWidget.click();
            headerWidget.accountsWidget.waitForVisible();
            headerWidget.accountsWidget.logoutLink.click(false);
        }
        return this;
    }

    public WebPage waitForPageLoad() {
        if (!(sel.getDriver() instanceof HtmlUnitDriver)) {
            super.waitForPageLoad();
            loginWidget.waitForPresentAndVisible(pageWaitTime);
        }
        return this;
    }

    public void validate() {
        loginWidget.validate();
    }

}
