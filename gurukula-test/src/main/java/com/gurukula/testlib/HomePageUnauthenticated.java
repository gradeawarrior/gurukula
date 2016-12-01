package com.gurukula.testlib;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;

public class HomePageUnauthenticated extends GurukulaPage {

    public HomePageUnauthenticated(Selenium sel) {
        super(sel);
        url = "/#/";
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
        loginWidget.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        loginWidget.validate();
    }

}
