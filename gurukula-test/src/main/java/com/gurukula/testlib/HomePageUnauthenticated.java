package com.gurukula.testlib;

import com.github.seleniumpm.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.net.URI;

public class HomePageUnauthenticated extends GurukulaPage {

    public HomePageUnauthenticated(WebDriver driver, URI server) {
        super(driver, server);
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
        if (!(driver instanceof HtmlUnitDriver)) {
            super.waitForPageLoad();
            loginWidget.waitForPresentAndVisible(pageWaitTime);
        }
        return this;
    }

    public void validate() {
        loginWidget.validate();
    }

}
