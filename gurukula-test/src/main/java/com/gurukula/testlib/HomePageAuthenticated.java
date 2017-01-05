package com.gurukula.testlib;

import com.github.seleniumpm.Selenium;
import com.github.seleniumpm.pagemodel.WebPage;

import java.util.concurrent.TimeUnit;

public class HomePageAuthenticated extends HomePageUnauthenticated {

    public HomePageAuthenticated(Selenium sel) {
        super(sel);
    }

    public WebPage waitForPageLoad() {
        // TODO - Adding a little sleep to help things along when waiting for elements to show up on the page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        headerWidget.accountsWidget.logoutLink.waitForPresent(pageWaitTime);
        return this;
    }

    public void validate() {
        // TODO - Apparently setting to 0 does not actually trigger a timeout immediately.
        // loginWidget.loginLink.notPresentAndVisible();
        // loginWidget.registerAccountLink.notPresentAndVisible();
        headerWidget.accountsWidget.logoutLink.present();
    }
}
