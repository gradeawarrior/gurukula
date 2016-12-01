package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;

import java.util.concurrent.TimeUnit;

public class HomePageAuthenticated extends HomePageUnauthenticated {

    public HomePageAuthenticated(Selenium sel) {
        super(sel);
    }

    public WebPage waitForPageLoad() {
        headerWidget.accountsWidget.logoutLink.waitForPresent(TimeUnit.MILLISECONDS.toSeconds(pageWaitTime));
        return this;
    }

    public void validate() {
        loginWidget.loginLink.notPresentAndVisible();
        loginWidget.registerAccountLink.notPresentAndVisible();
        headerWidget.accountsWidget.logoutLink.present();
    }
}
