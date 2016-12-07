package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;

import java.util.concurrent.TimeUnit;

public class HomePageAuthenticated extends HomePageUnauthenticated {

    public HomePageAuthenticated(Selenium sel) {
        super(sel);
    }

    public WebPage waitForPageLoad() {
        headerWidget.accountsWidget.logoutLink.waitForPresent(pageWaitTime);
        return this;
    }

    public void validate() {
        // TODO - Apparently setting to 0 does not actually trigger a timeout immediately. Could be a communication issue using RemoteWebDriver
        // loginWidget.loginLink.notPresentAndVisible();
        // loginWidget.registerAccountLink.notPresentAndVisible();
        headerWidget.accountsWidget.logoutLink.present();
    }
}
