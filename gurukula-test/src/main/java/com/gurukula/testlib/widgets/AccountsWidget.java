package com.gurukula.testlib.widgets;

import com.gurukula.ui.Selenium;
import com.gurukula.ui.pagemodel.webelements.Link;
import com.gurukula.ui.pagemodel.webelements.Widget;
import org.openqa.selenium.By;

public class AccountsWidget extends Widget {
    public Link logoutLink;

    public AccountsWidget(Selenium sel, Object locator) {
        super(sel, locator);
        logoutLink = new Link(sel, By.xpath("//a[@ng-click='logout()']"));
    }

    public AccountsWidget waitForPresentAndVisibleSelected() {
        logoutLink.waitForPresentAndVisible();
        return this;
    }

    public void validate() {
        logoutLink.presentAndVisible();
    }
}
