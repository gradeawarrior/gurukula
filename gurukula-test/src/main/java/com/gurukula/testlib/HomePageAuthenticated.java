package com.gurukula.testlib;


import com.github.seleniumpm.WebPage;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class HomePageAuthenticated extends HomePageUnauthenticated {

    public HomePageAuthenticated(WebDriver driver, URI server) {
        super(driver, server);
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
