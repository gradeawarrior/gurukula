package com.gurukula.testlib;

import com.gurukula.testlib.widgets.AccountsWidget;
import com.gurukula.testlib.widgets.HeaderWidget;
import com.gurukula.testlib.widgets.LoginWidget;
import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.WebPage;
import com.gurukula.ui.pagemodel.webelements.Link;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;

public class GurukulaPage extends WebPage {
    public LoginWidget loginWidget;
    public HeaderWidget headerWidget;

    public GurukulaPage(Selenium sel) {
        super(sel);
        loginWidget = new LoginWidget(sel, By.xpath("//div[contains(@class, 'col-md-8')]/div[@ng-switch='isAuthenticated()']"));
        headerWidget = new HeaderWidget(sel, By.id("navbar-collapse"));
    }

    public GurukulaPage(Selenium sel, String url) {
        super(sel, url);
        loginWidget = new LoginWidget(sel, By.xpath("//div[contains(@class, 'col-md-8')]/div[@ng-switch='isAuthenticated()']"));
        headerWidget = new HeaderWidget(sel, By.id("navbar-collapse"));
    }

    public boolean isLoggedIn() {
        return (!loginWidget.loginLink.isPresentAndVisible() && !loginWidget.registerAccountLink.isPresentAndVisible()) || headerWidget.accountsWidget.logoutLink.isPresent();
    }

    public WebPage waitForPageLoad() {
        headerWidget.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        throw new NotImplementedException();
    }
}
